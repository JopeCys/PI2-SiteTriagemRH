import matplotlib.pyplot as plt
import numpy as np

# Dados dos testes para Cadastro de Vaga e Cadastro de Candidato
usuarios_simultaneos = [20, 100, 500]

latencia = {
    "Cadastro de Vaga (1º)": [0.921076, 2.465706, 158.089508],
    "Cadastro de Candidato (1º)": [0.970612, 3.768376, 668.261829],
    "Cadastro de Vaga (2º)": [1.056001, 1.776016, 221.018224],
    "Cadastro de Candidato (2º)":[0.976241, 1.923262, 155.063364],

}

vazao = {
    "Cadastro de Vaga (1º)": [299, 2040, 7082],  # Vazão ligeiramente menor com mais concorrência
    "Cadastro de Candidato (1º)": [302, 2046, 5659],  # Vazão reduzida mais drasticamente à medida que a concorrência aumenta
    "Cadastro de Vaga (2º)": [301, 2064, 7155],
    "Cadastro de Candidato (2º)":[303, 2080, 8712],
}
# Ajuste dos valores no eixo X para refletir o espaçamento desejado
x_positions = [0, 1, 3]  # Posições personalizadas para os ticks

# Criação dos gráficos
fig, axs = plt.subplots(1, 2, figsize=(14, 6))

# Gráfico de Latência
width = 0.2  # Largura das barras
axs[0].bar(np.array(x_positions) - 1.5*width, latencia["Cadastro de Vaga (1º)"], width=width, color='red', label='Cadastro de Vaga (1º)')
axs[0].bar(np.array(x_positions) - 0.5*width, latencia["Cadastro de Candidato (1º)"], width=width, color='blue', label='Cadastro de Candidato (1º)')
axs[0].bar(np.array(x_positions) + 0.5*width, latencia["Cadastro de Vaga (2º)"], width=width, color='orange', label='Cadastro de Vaga (2º)')
axs[0].bar(np.array(x_positions) + 1.5*width, latencia["Cadastro de Candidato (2º)"], width=width, color='green', label='Cadastro de Candidato (2º)')
axs[0].set_title('Latência')
axs[0].set_xlabel('Usuários Simultâneos')
axs[0].set_ylabel('Latência (ms)')
axs[0].set_xticks(x_positions)
axs[0].set_xticklabels(usuarios_simultaneos)
axs[0].legend()
axs[0].grid(True)

# Gráfico de Vazão
axs[1].bar(np.array(x_positions) - 1.5*width, vazao["Cadastro de Vaga (1º)"], width=width, color='red', label='Cadastro de Vaga (1º)')
axs[1].bar(np.array(x_positions) - 0.5*width, vazao["Cadastro de Candidato (1º)"], width=width, color='blue', label='Cadastro de Candidato (1º)')
axs[1].bar(np.array(x_positions) + 0.5*width, vazao["Cadastro de Vaga (2º)"], width=width, color='orange', label='Cadastro de Vaga (2º)')
axs[1].bar(np.array(x_positions) + 1.5*width, vazao["Cadastro de Candidato (2º)"], width=width, color='green', label='Cadastro de Candidato (2º)')
axs[1].set_title('Vazão')
axs[1].set_xlabel('Usuários Simultâneos')
axs[1].set_ylabel('Vazão (requisições por segundo)')
axs[1].set_xticks(x_positions)
axs[1].set_xticklabels(usuarios_simultaneos)
axs[1].legend()
axs[1].grid(True)  


# Exibir os gráficos
plt.tight_layout()
plt.show()