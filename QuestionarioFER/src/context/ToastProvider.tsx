import { createContext, useContext, useEffect, useState } from "react";
import { toast, Toaster } from "react-hot-toast";

const ToastContext = createContext<{
  showToast: (type: "success" | "error", message: string) => void;
  setPersistentToast: (type: "success" | "error", message: string) => void;
}>({ showToast: () => {}, setPersistentToast: () => {} });

export const ToastProvider: React.FC<{ children: React.ReactNode }> = (
  props
) => {
  const [toastConfig, setToastConfig] = useState<{
    type: "success" | "error";
    message: string;
  } | null>(null);

  const showToast = (type: "success" | "error", message: string) => {
    switch (type) {
      case "success":
        toast.success(message);
        break;
      case "error":
        toast.error(message);
        break;
      default:
        break;
    }
  };
  const setPersistentToast = (type: "success" | "error", message: string) => {
    setToastConfig({ type, message });
  };

  // Mostra il toast persistente quando cambia pagina
  useEffect(() => {
    if (toastConfig) {
      showToast(toastConfig.type, toastConfig.message);
      setToastConfig(null); // Resetta il messaggio persistente
    }
  }, [toastConfig]);

  return (
    <ToastContext.Provider value={{ showToast, setPersistentToast }}>
      {props.children}
      <Toaster />
    </ToastContext.Provider>
  );
};

export const useToast = () => useContext(ToastContext);
