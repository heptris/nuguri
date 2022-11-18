import withAuth from "@/utils/withAuth";
import { Text } from "@common/components";

const ApplyPage = () => {
  return (
    <div>
      <Text>신청페이지</Text>
    </div>
  );
};

export default withAuth(ApplyPage);
