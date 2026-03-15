export interface Usuario { id: number; nome: string; email: string; perfil: 'ACIONAMENTO'|'ANALISTA'|'ADMIN'; ativo: boolean; }
export interface LoginRequest { email: string; senha: string; }
export interface LoginResponse { token: string; nome: string; email: string; perfil: string; }
