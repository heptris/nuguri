import { useHeader } from "@/hooks";

const NotificationPage = () => {
  useHeader({ mode: "ITEM", headingText: "알림" });

  return <div>NotificationPage</div>;
};

export default NotificationPage;
