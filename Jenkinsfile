import java.time.*
import java.time.format.DateTimeFormatter

Calendar calendar
def dia

pipeline {
   agent any
   stages {
      stage('Fecha de hoy') {
         steps {
            script{
                calendar = Calendar.getInstance()
                dia = calendar.get(Calendar.DAY_OF_WEEK)
                def diasSemana=[
                       1:"Domingo",
                       2:"Lunes",
                       3:"Martes",
                       4:"Miercoles",
                       5:"Jueves",
                       6:"Viernes",
                       7:"Sabado"]
                def fecha = new Date()
                println "Hoy es " + diasSemana[dia] + " " + fecha.format("dd/MM/YYYY")
            }
         }
      }
      stage("Chiste Informático"){
          steps{
              script{
                  if(dia == 5){
                      echo "¿Que le dice un bit al otro? Nos vemos en el bus."
                  }
              }
          }
      }
      stage("Chiste Humor negro"){
          steps{
              script{
                if(dia == 6){
                    echo "¿Qué es peor que 20 bebés en un contenedor?, Un bebé en 20 contenedores."
                }  
              }
          }
      }
      stage("Chiste Futbol"){
          steps{
              script{
                if(dia == 2){
                      echo "FC Barcelona (El chiste)."
                }
              }
          }
      }
      stage("Chiste Politica"){
          steps{
              script{
                if(dia == 3){
                      echo "La política es semejante a la religión; la diferencia es que en política cada uno confiesa los pecados del adversario"
                }
              }
          }
      }
      stage("Chiste Medicos"){
          steps{
              script{
               if(dia == 4){
                    echo "Un paciente salía tristemente del Hospital cuando lo asaltan en la puerta y el ladrón le dice:"    
                    echo "- ¡El dinero o la vida!"
                    echo "- ¡Ah! ¿Tú también eres médico?"
                }   
              }
          }
      }
   }
}
