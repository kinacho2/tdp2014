using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Plugin;

namespace Plugin
{
    public class PluginSuma : IPlugin
    {
        public string Name { get { return "Suma"; } }
        public string Descripcion { get { return "Operacion que permite sumar algebraicamente dos numeros reales."; } }

        public double calcular(double a, double b)
        {
            return a + b;
        }
    }

}
