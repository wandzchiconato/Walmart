package walmart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

//@RunWith(Parameterized.class)
public class DaConsultaAoCarrinho_Cucumber {
	String baseUrl;
	WebDriver driver;
	String pastaPrint = new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime());
	// teste
	// Métodos de Apoio
	// Método de Print de Tela
	public void Print(String nomePrint) throws IOException {
		File foto =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(foto,
		new File("D:\\git\\Walmart\\Wallmart\\target\\evidencias\\" + pastaPrint + "\\" + nomePrint + ".png"));
	}
	
	// Método de Leitura de Dados
    // Atributos de Massa de Teste
	private String id;
	private String termo = "smartphone";
	private String resultado = "smartphone";
	private String browser;
/*	
	// Construtor
	
	public DaConsultaAoCarrinho_Cucumber(String id, String termo, String resultado, String browser) {
		this.id = id;
		this.termo = termo;
		this.resultado = resultado;
		this.browser = browser;
	}
*/	
	
/*	
	@Parameters
	public static Collection <String[]> LerArquivo() throws IOException {
		return LerCSV("C:\\Users\\Gabriel Correia\\eclipse-workspace\\Walmart\\db\\massa.csv");
	}
	
    // Não Mexa
	private static Collection<String[]> LerCSV(String nomeMassa) throws IOException {
		List<String[]> dados = new ArrayList<String[]>();
		String linha;
		BufferedReader arquivo = new BufferedReader(new FileReader(nomeMassa));
		while((linha = arquivo.readLine()) != null) {
			String campos[] = linha.split(";");
			dados.add(campos);
		}
		arquivo.close();
				
		return dados;
	}
*/
	@Before
	public void Iniciar() {
		baseUrl = "https://www.submarino.com.br";
		System.setProperty("webdriver.chrome.driver", 
		"D:\\Eclipse\\Drivers\\Chrome\\chromeDriver74\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}


	@After
	public void Finalizar() {
		driver.quit();
	}
	
	@Given("^que acesso o site Submarino$")
	public void que_acesso_o_site_Walmart() throws Throwable {
		driver.get(baseUrl);
		System.out.println("Passo 1 - Abriu o site");
		Print("TC0021 - Caminho Feliz - id " + id + " - Passo 1 - Home Walmart");
	}

	@When("^pesquiso por \"([^\"]*)\" e pressiona Enter$")
	public void pesquiso_por_e_pressiona_Enter(String Termo) throws Throwable {
		driver.findElement(By.id("h_search-input")).clear();
		// Usando o parametro termo a partir do arquivo feature
		// driver.findElement(By.id("suggestion-search")).sendKeys(Keys.chord(Termo));
		// Usando uma função de leitura de arquivo CSV
		driver.findElement(By.id("h_search-input")).sendKeys(Keys.chord(termo));
		System.out.println("Passo 2 - Digitou o termo de busca");
		Print("TC0021 - Caminho Feliz - id " + id + " - Passo 2 - Consulta Produto");
		driver.findElement(By.id("h_search-input")).sendKeys(Keys.ENTER);
	}

	@Then("^exibe a lista de produtos relacionados com \"([^\"]*)\"$")
	public void exibe_a_lista_de_produtos_relacionados_com(String Resultado) throws Throwable {
		
		// Usando o parametro Resultado a partir do arquivo feature
		// String resultadoEsperado = "Resultados de busca para \"" + Resultado + "\"";
		// Usando uma função de leitura de arquivo CSV
		String resultadoEsperado = resultado;
		String resultadoObtido = driver.findElement(By.cssSelector("h1.h1.page-title")).getText();
		// Comparar se os 2 textos são iguais
		assertEquals(resultadoEsperado,resultadoObtido);
		// Verificar se o texto da página contém o resultado
		assertTrue(resultadoObtido.contains(resultado));
		System.out.println("Passo 3 - Validou o titulo da lista de produtos");
		Print("TC0021 - Caminho Feliz - id " + id + " - Passo 3 - Valida Titulo da Lista");
	}
	
		
}
