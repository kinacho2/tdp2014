using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Plugin
{
    public class PluginSuma : IPlugin
    {
        public string Name { get { return "Sumatoria"; } }
        public string Descripcion { get { return "Operacion que realiza una sumatoria desde el primer operando hasta el segundo operando ingresado."; } }

        public double calcular(double a, double b)
        {
            double resu=0;
            while (a <= b)
            {
                resu += a;
                a++;
            }
            return resu;
        }
    }

}
