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
  fee: number;
  ageLimit: number;
  sexLimit?: "f" | "m";
  hobbyImage: string;
  closed: boolean;
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

export type { UserInfoType, HobbyRoomType, DealItemDetailType };
