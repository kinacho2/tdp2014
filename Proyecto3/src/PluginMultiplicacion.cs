using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Plugin
{
    public class PluginResta : IPlugin
    {
        public string Name { get { return "Multiplicacion"; } }
        public string Descripcion { get { return "Operacion que permite realizar una multiplicacion entre enteros."; } }

        public double calcular(double a, double b)
        {
            return a*b;
        }
    }

}