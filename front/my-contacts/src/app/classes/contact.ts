import {Phone} from "./phone";
import {Address} from "./address";

export class Contact {

  id: number;
  createdDate: string;
  firstName: string;
  lastName: string;
  email: string;
  phones: Phone[];
  addreess: Address[];


}
