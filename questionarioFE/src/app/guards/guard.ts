import { inject } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from "@angular/router";
import { jwtDecode } from "jwt-decode";

export const Guard : CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    const router = inject(Router)
    let token = sessionStorage.getItem("token")
    console.log(token)
    if(token) {
        try{
            const decodedToken: any = jwtDecode(token)
            console.log("decoded token", decodedToken)
            if(decodedToken.isAdmin === "1") {
                return true
            } else {
                console.log("User is not admin")
                router.navigateByUrl("home")
                return false
            }
        } catch (error) {
            console.log(error)
            router.navigateByUrl("questionrio")
            return false
        }
    } else {
        router.navigateByUrl("login");
        return false;
      }
}