import { cardContainerProps } from "../models/card";

const CardContainer = ({ title, children, className }: cardContainerProps) => {
  return (
    <div className={`container mt-5 py-5 ${className}`}>
      <div className="row justify-content-center">
        <div className="col-12 col-sm-8 col-md-6 col-lg-4">
          <div className="bg-light p-5 rounded shadow">
            <h2 className="text-center">{title}</h2>
            {children}
          </div>
        </div>
      </div>
    </div>
  );
};

export default CardContainer;
