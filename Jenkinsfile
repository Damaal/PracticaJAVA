import java.util.Date;
pipeline {
   agent any
   stages {
      stage('Fecha de hoy') {
         steps {
            script{
               Date fecha = new Date();
               System.out.println(fecha);
            }
         }
      }
   }
}
