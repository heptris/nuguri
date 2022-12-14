type UserInfoType = {
  baseAddress: string;
  description?: string;
  email: string;
  localId: number;
  memberId: number;
  name: string;
  nickname: string;
  profileImage?: string;
  temperature: number;
};

type HobbyRoomType = {
  hobbyId: number;
  localId: number;
  categoryId: number;
  title: string;
  content: string;
  endDate: string;
  meetingPlace: string;
  curNum: number;
  maxNum: number;
  wishlistNum: number;
  chatNum: number;
  fee: number;
  highAgeLimit?: number;
  rowAgeLimit?: number;
  sexLimit?: "f" | "m";
  imageurl: string;
  closed: boolean;
  approveStatus: string;
  nickname: string;
  profileImage: string;
};

type DealItemDetailType = {
  categoryId: number;
  localId: number;
  sellerNickname: string;
  dealId: number;
  title: string;
  description: string;
  price: number;
  hit: number;
  dealImage: string;
  dong: string;
  sellerId: number;
  deal: boolean;
  favorite?: boolean;
};

type DealItemType = {
  categoryId: number;
  deal: boolean;
  dealId: number;
  dealImage: string;
  description: string;
  hit: number;
  localId: number;
  price: number;
  title: string;
};

type HobbyMemberType = {
  [x: string]: any;
  hobbyHistoryId: number;
  hobbyId: number;
  memberId: number;
  approveStatus: string;
  nickname: string;
  imageUrl: string;
  promoter: boolean;
};
export type { UserInfoType, HobbyRoomType, DealItemDetailType, DealItemType, HobbyMemberType };
