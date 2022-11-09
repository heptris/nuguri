type LoginFormType = {
  email: string;
  password: string;
};
type ChatRoomInfoType = {
  dealId?: number;
  hobbyId?: number;
  isOneToOne: boolean;
  receiverId?: number;
  roomName?: string;
  senderId: number;
};

export type { LoginFormType, ChatRoomInfoType };
