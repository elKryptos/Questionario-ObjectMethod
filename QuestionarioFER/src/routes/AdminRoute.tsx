import { Navigate } from "react-router-dom";
import { useAuth } from "../context/AuthProvider";
import toast from "react-hot-toast";

export default function AdminRoute({
  children,
}: {
  children: React.ReactNode;
}) {
  const { isAdmin, loading } = useAuth();

  if (loading) {
    return (
      <div className="d-flex justify-content-center">
        <div className="spinner-border text-light" role="status">
          <span className="visually-hidden">Loading...</span>
        </div>
      </div>
    );
  }

  if (!isAdmin) {
    // Reindirizza al login se non amministratore
    toast.error("Non sei un amministratore.");
    return <Navigate to="/" />;
  }
  return children;
}
