Feature: ConsultaCompra
	Usu�rios
	Patricia � uma cliente frequente
	Wagner � um visitante eventual
	Scenario: Caminho Feliz
		Given que acesso o site Submarino
		When pesquiso por "smartphone" e pressiona Enter
		Then exibe a lista de produtos relacionados com "smartphone"