type LoginFormType = {
  email: string;
  password: string;
};

export type ChatRoomType = "DEAL" | "HOBBY";
type ChatRoomInfoDefaultType = { senderId: number };
type DealChatRoomInfoType = ChatRoomInfoDefaultType & { receiverId: number };
type HobbyChatRoomInfoType = ChatRoomInfoDefaultType & { roomName: string };
type ChatRoomInfoFindType<T extends ChatRoomType> = T extends "DEAL" ? DealChatRoomInfoType & { dealHisotyId: number } : HobbyChatRoomInfoType & { hobbyId: number };
type ChatRoomInfoGetHistoryType<T extends ChatRoomType> = (T extends "DEAL" ? DealChatRoomInfoType : HobbyChatRoomInfoType) & { roomId: string };

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
