type LoginFormType = {
  email: string;
  password: string;
};

export type ChatRoomType = "DEAL" | "HOBBY";

type ChatRoomInfoType<ChatRoomType> = { senderId?: number } & (ChatRoomType extends "DEAL" ? { receiverId: number } : { roomName: string });

type ChatRoomInfoFindType<T extends ChatRoomType> = ChatRoomInfoType<T> & (T extends "DEAL" ? { dealHistoryId: number } : { hobbyId: number });

type ChatRoomInfoGetHistoryType<T extends ChatRoomType> = { roomId: string } & ChatRoomInfoType<T>;

type ChatMessageType = "ENTER" | "TALK" | "LEAVE";
type ChatRoomMessageInfoType<T extends ChatMessageType> = {
  messageType: T;
  senderId: number;
  roomId: string;
} & (T extends "TALK" ? { message: string } : {});

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

export type { LoginFormType, ChatRoomInfoFindType, ChatRoomInfoGetHistoryType, ChatRoomMessageInfoType, DealItemDetailType };
