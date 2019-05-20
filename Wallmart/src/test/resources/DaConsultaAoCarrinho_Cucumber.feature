Feature: ConsultaCompra
	Usuários
	Patricia é uma cliente frequente
	Wagner é um visitante eventual
	Scenario: Caminho Feliz
		Given que acesso o site Submarino
		When pesquiso por "smartphone" e pressiona Enter
		Then exibe a lista de produtos relacionados com "smartphone"