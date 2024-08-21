import matplotlib.pyplot as plt
import numpy as np

# Dados dos testes para Cadastro de Vaga e Cadastro de Candidato
usuarios_simultaneos = [20, 100, 500]

latencia = {
    "Cadastro de Vaga": [324.36, 324.36, 324.36],
    "Cadastro de Candidato": [161.92, 570.86, 711.6],
}

vazao = {
    "Cadastro de Vaga": [157.48, 150.00, 140.00],  # Vazão ligeiramente menor com mais concorrência
    "Cadastro de Candidato": [192.23, 150.00, 100.00],  # Vazão reduzida mais drasticamente à medida que a concorrência aumenta
}

# Criação dos gráficos
fig, axs = plt.subplots(1, 2, figsize=(14, 6))

# Gráfico de Latência
width = 20  # Largura das barras
axs[0].bar(np.array(usuarios_simultaneos) - width/2, latencia["Cadastro de Vaga"], width=width, color='blue', label='Cadastro de Vaga')
axs[0].bar(np.array(usuarios_simultaneos) + width/2, latencia["Cadastro de Candidato"], width=width, color='orange', label='Cadastro de Candidato')
axs[0].set_title('Latência')
axs[0].set_xlabel('Usuários Simultâneos')
axs[0].set_ylabel('Latência (ms)')
axs[0].set_xticks(usuarios_simultaneos)
axs[0].legend()

# Gráfico de Vazão
axs[1].bar(np.array(usuarios_simultaneos) - width/2, vazao["Cadastro de Vaga"], width=width, color='blue', label='Cadastro de Vaga')
axs[1].bar(np.array(usuarios_simultaneos) + width/2, vazao["Cadastro de Candidato"], width=width, color='orange', label='Cadastro de Candidato')
axs[1].set_title('Vazão')
axs[1].set_xlabel('Usuários Simultâneos')
axs[1].set_ylabel('Iterações (requisições por segundo)')
axs[1].set_xticks(usuarios_simultaneos)
axs[1].legend()

# Exibir os gráficos
plt.tight_layout()
plt.show()