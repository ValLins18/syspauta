package com.example.syspauta.syspauta.Model.Enums;

public enum StatusPauta {
    CRIADA,     // Pauta criada mas sessão ainda não aberta
    ABERTA,     // Sessão de votação em andamento
    FECHADA,    // Sessão encerrada mas votos não apurados
    APROVADA,   // Pauta aprovada pela maioria dos votos
    REJEITADA   // Pauta rejeitada pela maioria dos votos
}
