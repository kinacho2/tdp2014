using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Plugin
{
    public class PluginResta : IPlugin
    {
        public string Name { get { return "Potencia"; } }
        public string Descripcion { get { return "Operacion que permite hacer operando 1 elevado a la operando 2."; } }

        public double calcular(double a, double b)
        {
            return Math.Pow(a, b);
        }
    }

}