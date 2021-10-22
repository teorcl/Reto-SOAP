Feature: Bandera del pais
  Como turista necesito conocer la bandera de un pais segun las siglas del nombre del pais
  para poder viajar

  Background:
    Given que el usuario de la  app quiere conocer la bandera del pais a donde desea viajar

  Scenario: Iniciales correctas del nombre del  pais
    When el usuario quiere conocer la bandera del EEUU e ingresa las iniciales "USA"
    Then el ususario deberia obtener la bandera con el nombre "USA"

  Scenario: Iniciales incorrectas del nombre del  pais
    When el usuario quiere conocer la bandera del Colombia e ingresa las iniciales "colombia"
    Then el ususario deberia obtener la respuesta  "Country not found in the database"