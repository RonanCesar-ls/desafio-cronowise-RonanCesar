1.3 Você tem uma lista com 1 milhão de registros. Precisa verificar se um CPF específico já existe nessa lista. Você faria isso com:
(a) Percorrer a lista do início ao fim comparando cada item
(b) Transformar a lista em um conjunto (set/HashSet) e usar busca direta
Explique em 3–5 linhas por que uma abordagem é melhor que a outra e em que situação você escolheria cada uma.
RESPOSTA: B - A primeira opção nos obriga a fazer 1 milhão deverificação desnecessaria percorrendo essa lista, se utilizarmos, se utilizarmos
o HashSet, ele vai calcular o endereço do cpf na memoria. 


Bloco 2 — Leitura de Código Python
2.1 O que esse código faz?
Descreva em até 5 linhas o comportamento geral desta função. O que ela recebe, o que tenta fazer, e o que retorna em cada cenário possível?
RESPOSTA: A função recebe um hash de CPF e tenta consultar um serviço externo, se tudo der certo ele retorna um "OK" junto com os dados encontrados. 
Se tiver erro ele trata dependendo do tipo se for um erro permanente ("PERMANENT"), ela simplesmente para e retorna o erro. Já se for temporário "TEMPORARY",
ela tenta novamente algumas vezes, esperando um tempo maior a cada tentativa. Depois de três tentativas se não der certo ele retorna um "ERRO_TEMPORARIO_ESGOTADO"
e se o erro não tiver incluso em nada disso, ele so segue em frente. 

Rastreando a execução
Simule mentalmente o que acontece quando consultar_credito("abc123") é chamado e o serviço externo retorna um ConsultaError do tipo TEMPORARY nas 3 primeiras chamadas.

• Quantas vezes a função é chamada no total?
• Quais são os tempos de espera entre cada tentativa?
• Qual é o retorno final?
RESPOSTA: Vai fazer 3 tentativas, espera um pouco mais a cada tentativa, na primeira 2segundos na segunda vai ser 4 segundos, como todas vai falhar como erro
temporario, vai desistir na 3 e retorna que as tentativas se esgotaram. 

2.3 Identificando um problema
Há um problema de design nesta implementação que pode causar falhas em produção em cenários de alta carga. Você consegue identificar? 
Descreva o problema e como você corrigiria.
RESPOSTA: O erro ta aqui "return consultar_credito(cpf_hash, tentativa + 1)", Cada vez que a função chama a si mesma, ela n vai descartar a versão anterior 
vai empilha tudo na memoria aguardando a resposta. Num sistema que recebe milhares de acessos simultaneos isso e uma bomba, essa pilha vai crescer absurdamente 
rapido e consumindo muita mémoria do servidor, fazendo o sistema cair. De solução da pra resolver com um for, a gente pode criar um "for tentativa in range (1, 4)"
que ai tenta conectar se der erro temporário, adiciona o time.sleep(), ai ele pausa e mais tarde ele tenta denovo, mas da também pra usar asyncio, ai usa o 
"await asyncio.sleep()", pq ai ao invés dele parar tudo, ele vai congelar só esse erro, vai continuar o programa e depois trata o erro. 


2.4 Proposta de melhoria
Se você precisasse adicionar um timeout máximo de 30 segundos para toda a operação (incluindo todas as tentativas e esperas), como faria? Não precisa escrever 
o código completo — descreva a abordagem em texto ou pseudocódigo.
RESPOSTA: De forma mais bruta, da pra adicionar logo na primeira linha um "inicio = time.time()", ai dentro do "for" a gente ja calcula quanto tempo passou, com 
um "tempo_gasto = tempo_atual - incio", ai se o tempo gasto for maior que 30 segundos, interrompe na hora e retorna erro de timeout, ai se a tentativa falaha e o 
robo precisa ficar inativo por uns 10 segundos, a gente checa se esses 10 segundos não ultrapassam os 30. Supondo que tenha so 2 segundos de sobra, não faz sentido 
ele parar por 10 segundos, ai a gente aborta a operação ali mesmo ou deixa ele ficar inativo so pelos 2 segundos restantes e encerra. 

3.1 Java: Entendendo uma classe de domínio
Pergunta: Após o código abaixo ser executado, qual será o valor de status e tentativas?

SolicitacaoCredito s = new SolicitacaoCredito("req-001", "hash-abc");
s.registrarTentativa(false);
s.registrarTentativa(false);
System.out.println(s.podeRetentar()); // O que imprime?
s.registrarTentativa(false);
System.out.println(s.podeRetentar()); // O que imprime agora?
• O que cada println imprime e por quê?
• Qual o estado final (status e tentativas) após as três chamadas?
RESPOSTA: Imprime TRUE. Por quê Antes desse print, o método registrarTentativa(false) já tinha sido chamado duas vezes, então o contador de tentativas chegou em 2. 
Como ainda não atingiu o limite que e 3, o código segue pelo caminho normal e define o status como ERRO_TEMPORARIO.
E o status final e StatusSolicitacao.FALHA_DEFINITIVA, e 3 tentativas

3.2 React/TypeScript: Lendo um componente
Perguntas:

(a) O que acontece na tela quando o componente é renderizado pela primeira vez, antes de a requisição completar?
RESPOSTA: A tela vai mostrar o texto "CARREGANDO..."
(b) Se tenantId mudar de "tenant-a" para "tenant-b" enquanto o componente está na tela, o que acontece? Por quê?
RESPOSTA: O useEffect vai perceber a mudança e rodar novamente, disparando uma nova requisição fetch para a API buscando os dados do tenant-b, 
e isso vai ocorrer porque o tenantId foi colocado na lista de dependências do hook.
(c) Há um problema neste componente que pode causar um bug visual. Você consegue identificar?
RESPOSTA: E pq quando o tenanteId muda e o useEffect roda denovo, o codigo não redefine o loading pra true. 
