import { useHeader } from "@/hooks";

const LoungePage = () => {
  useHeader({ mode: "LIST", headingText: "너구리 라운지" });

  return <div>LoungePage</div>;
};

export default LoungePage;
