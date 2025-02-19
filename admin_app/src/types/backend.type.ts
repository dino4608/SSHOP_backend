export interface IApiRes<T> {
    success: boolean;
    code: number;
    error: string;
    meta: any;
    data: T;
}

export interface IAccount {
    authenticated: boolean;
    token: string;
}