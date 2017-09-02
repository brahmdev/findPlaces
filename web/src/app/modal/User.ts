export class User {
  username: string;
  firstName: string;
  lastName: string;
  email: string;
  roles: string[];
  highestRole: string;
  sessionTimeout: number;
  expiresOn: number;
}
