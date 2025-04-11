import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { finalize, Observable } from "rxjs";
import { ToastService } from "../service/toast.service";
import { Injectable } from "@angular/core";

@Injectable()
export class Tokeninterceptor implements HttpInterceptor {

    constructor(private toastService: ToastService) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
         // Mostra lo spinner prima di inviare la richiesta HTTP
        this.toastService.showSpinner()
        const token = sessionStorage.getItem("token")
        let cloneRequest = req
        if(token) {
            cloneRequest = req.clone({
                setHeaders: {
                "Authorization" : `Bearer ${token}`
                }
            })
        }
        // Gestisci la risposta HTTP
        return next.handle(cloneRequest).pipe(
            finalize(()=> {
                this.toastService.hideSpinner()
            })
        )
    }
}
