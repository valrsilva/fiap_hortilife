<!--?php &lt;br ?--> /* Envio de E-mail mail()
Arquivo enviaContato.php - PHP
***********************************************************
<?php
Error_reporting (0);
$EmailFrom = Trim(stripslashes($_POST['email']));
$EmailTo = "flp.ddjobs@gmail.com; contato@hefestosteelframe.com.br; capignatari@gmail.com; lucpignatari@gmail.com"; // <- Please add your email
$Subject = "Contato - Hefesto Steel frame";
// $Title = Trim(stripslashes($_POST['name']));
// $Phone = Trim(stripslashes($_POST['phone']));  
// $Message = Trim(stripslashes($_POST['message'])); 

// validation
// if (isset($_POST['g-recaptcha-response'])) {
//   $captcha_data = $_POST['g-recaptcha-response'];
// }

// Se nenhum valor foi recebido, o usuário não realizou o captcha
// if (!$captcha_data) {
//   echo "<script>alert('Por favor, confirme o captcha.'); window.location='http://hefestosteelframe.com.br/';</script>";
//   exit;
// }
// prepare email body text
$Body = "";
$Body .= "Nome: ";
$Body .= utf8_encode($Title);
$Body .= "\n";
$Body .= "E-mail: ";
$Body .= utf8_decode($EmailFrom);
$Body .= "\n";
$Body .= "\n";
$Body .= "Mensagem:\n";
$Body .= "Entre em contato no e-mail acima para mais detalhes.";
$Body .= "\n";

// send email 
$success = mail($EmailTo, utf8_encode($Subject), $Body, "From: <$EmailFrom>","-r".$EmailTo);
//$resposta = file_get_contents("https://www.google.com/recaptcha/api/siteverify?secret=6Ld_2H4UAAAAAAEJAiO0TiPDxDs1rem4n-N7ISOD&response=".$captcha_data."&remoteip=".$_SERVER['REMOTE_ADDR']);

// redirect to success page 
if ($success){ //&& $resposta.success){
  echo "<script>alert('Mensagem enviada com sucesso! Em breve entraremos em contato.'); window.location='http://hefestosteelframe.com.br/';</script>";
}
else{
  echo "<script>alert('Erro ao enviar a mensagem. Tente novamente mais tarde.'); window.location='http://hefestosteelframe.com.br/';</script>";
}
?>