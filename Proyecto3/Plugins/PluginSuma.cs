using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Plugin
{
    class PluginSuma : IPlugin
    {
        public string Name { get { return "Suma"; } }
        public string Descripcion { get { return "Operacion que permite sumar algebraicamente dos numeros reales."; } }

        public double calcular(double a, double b)
        {
            return a + b;
        }
    }

    class PluginDivision : IPlugin
        {
            public string Name { get { return "Division"; } }
            public string Descripcion { get { return "Operacion que permite dividir algebraicamente dos numeros reales."; } }

            public double calcular(double a, double b)
            {
                return a / b;
            }
        }

        class PluginPotencia : IPlugin
        {
            public string Name { get { return "Potenciacion"; } }
            public string Descripcion { get { return "Operacion que permite elevar el primer termino por el segundo."; } }

            public double calcular(double a, double b)
            {
                return Math.Pow(a, b);
            }
        }
}
