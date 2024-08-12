import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    stages: [
        { duration: '30s', target: 10 }, // Sobe para 10 usuários simultâneos em 30 segundos
        { duration: '1m', target: 20 },  // Mantém 20 usuários simultâneos por 1 minuto
        { duration: '30s', target: 0 },  // Reduz para 0 usuários em 30 segundos
    ],
};

export default function () {
    const url = `http://localhost:8080/vagas`;

    // Envia a requisição GET para ler os detalhes da vaga
    const res = http.get(url);

    // Verifica se o status da resposta foi 200 (OK)
    check(res, {
        'status was 200': (r) => r.status === 200,
        'response time was < 500ms': (r) => r.timings.duration < 500,
    });

    // Pequena pausa para simular o tempo entre ações de um usuário
    sleep(1);
}
