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
};

type DealItemDetailType = {
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

export type { UserInfoType, HobbyRoomType, DealItemDetailType, DealItemType };
