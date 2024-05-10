import { Profit } from "./profit";

export interface Stock {
  id:string;
  name:string;
  price_medium:number;
  category:string;
  profits?: Profit[];
  totalPag?: number;
}
