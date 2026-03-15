export interface Chamado { id: number; titulo: string; descricao: string; status: string; prioridade: string; projetoId: number; analistaId?: number; abertoEm: string; fechadoEm?: string; }
export interface Comentario { id: number; texto: string; chamadoId: number; autorNome: string; criadoEm: string; }
