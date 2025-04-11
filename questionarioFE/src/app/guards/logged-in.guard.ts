import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../service/auth.service';

export const loggedInGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  const router = inject(Router)
  const authService = inject(AuthService)
  if(authService.isLoggedIn()){
    router.navigateByUrl("home")
    return false
  } 
  return true
};
