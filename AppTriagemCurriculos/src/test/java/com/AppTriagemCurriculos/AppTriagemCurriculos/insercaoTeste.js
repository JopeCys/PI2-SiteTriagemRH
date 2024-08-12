import http from 'k6/http';
import { check, sleep } from 'k6';
import { Counter } from 'k6/metrics';

export const requests = new Counter('http_reqs');

export const options = {
    stages: [
        { duration: '30s', target: 10 }, // 10 usuários simultâneos em 30 segundos
        { duration: '1m', target: 20 },  // 20 usuários simultâneos por 1 minuto
        { duration: '30s', target: 0 },  // Desaceleração para 0 usuários em 30 segundos
    ],
    thresholds: {
        http_req_duration: ['p(95)<500'], // 95% das requisições devem ser completadas em menos de 500ms
    },
};

export default function () {
    const url = 'http://localhost:8080/cadastrarVaga';
    const payload = {
        nome: `Desenvolvedor Java  ${Math.random().toString(36).substring(7)}`,
        area: `T${Math.random().toString(36).substring(7)}`,
        descricao: 'Desenvolvimento de sistemas Java'
    };

    const params = {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
    };

     // Envia a requisição POST com os dados de formulário
    const res = http.post(url, payload, params);

    check(res, {
        'status was 200': (r) => r.status === 200,
        'response time was < 500ms': (r) => r.timings.duration < 500,
    });

    sleep(1);
}
