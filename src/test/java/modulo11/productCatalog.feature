Feature: AOS Product Catalog

  Scenario Outline: Verifica precos no Catalogo de Produtos
    Given user entra no portal "https://www.advantageonlineshopping.com/"
    When seleciona "<category>"
    And seleciona o primeiro produto
    Then preco mostrado "<price>"

    Examples: 
      | category | price   |
      | SPEAKERS | $200.00 |
      | MICE     | $29.99   |
