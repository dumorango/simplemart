simplemart
==========

Live Demo: http://107.178.221.29:3000/

Subir ambiente:
-------------

Iniciar o servidor Solr:

	cd solrserver
	java -jar start.jar
	

Iniciar o servidor REST:

	cd restserver
	mvn jetty:run
	
Iniciar o front-end webclient (desenvolvimento):

	cd webclient
	grunt serve
	
Rodar testes:
-

Testes na camada REST:
	
	cd restserver
	mvn test
	
Testes na camada Web:

	cd webclient
	grunt karma
	
	

	
	
	
