using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Collections;
using System.Reflection;
using Plugin;

namespace Proyecto3
{
    class Host
    {
        private ArrayList plugins;
        private DirectoryInfo directorio;
        private GUI gui;

        public Host(GUI gui)
        {
            plugins = new ArrayList();
            //Carpeta de plugins, dentro de bin
            directorio = new DirectoryInfo("../plugins");
            this.gui = gui;
            actualizarPlugins();
        }

        public ArrayList actualizarPlugins()
        {  
            foreach (string fileOn in Directory.GetFiles("../plugins")) 
            {
                FileInfo file = new FileInfo(fileOn);
                if (file.Extension.Equals(".dll"))
                {
                    cargarPlugin();
                }
            }
            return plugins;
        }

        private void cargarPlugin()
        {
            //Hay que terminar toda la parte de la carga practicamente
            Assembly assembly = null;
            foreach (FileInfo f in plugins)
            {
                assembly = Assembly.LoadFile(f.FullName);
                plugins.Add(assembly);
            }

            
        }


        public String direct()
        {
            return directorio.FullName;
        }

    }
}
