import { useAlarm, useHeader } from "@/hooks";
import withAuth from "@/utils/withAuth";

const NotificationPage = () => {
  useHeader({ mode: "ITEM", headingText: "알림" });
  const { notiList } = useAlarm();

  console.log(notiList);

  return <div>NotificationPage</div>;
};

export default withAuth(NotificationPage);
