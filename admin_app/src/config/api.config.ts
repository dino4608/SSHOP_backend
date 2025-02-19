import { IAccount, IApiRes } from '../types/backend.type';
import axios from './axios.config';

const system = '/admin/api/v1';

const access = '/access';

export const callLogin = (username: string, password: string): Promise<IApiRes<IAccount>> => {
    return axios.post<IApiRes<IAccount>>(`${system}${access}/login`, { username, password }) as any;
}