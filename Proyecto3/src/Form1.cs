using System;
using System.Collections.Generic;
using System.ComponentModel;
//using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Collections;
using System.IO;
using Plugin;

namespace Proyecto3
{
    public partial class GUI : Form
    {
        private Host host;
        private List<IPlugin> lista;
        private int index;

        public GUI()
        {
            host = new Host(this);
            InitializeComponent();
            lista = host.actualizarPlugins();
            listaPlugins.DropDownStyle = ComboBoxStyle.DropDownList;
            
            foreach (IPlugin p in lista)
            {
                listaPlugins.Items.Add(p.Name);
            }
            if(listaPlugins.Items.Count!=0)
                listaPlugins.SelectedIndex = 0;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                int index = listaPlugins.SelectedIndex;
                IPlugin plugin = (IPlugin)lista[index];

                double n1 = System.Convert.ToDouble(textBox1.Text);
                double n2 = System.Convert.ToDouble(textBox2.Text);
                double resu = plugin.calcular(n1, n2);
                resultado.Text = "Resultado: " + resu.ToString();
            }
            catch (System.ArgumentOutOfRangeException)
            {
                MessageBox.Show("No se han seleccionado plugins o no se ha cargado ninguno.","Error",MessageBoxButtons.OK,MessageBoxIcon.Error,MessageBoxDefaultButton.Button1);
            }
            catch (System.FormatException)
            {
                MessageBox.Show("Use solo numeros.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error, MessageBoxDefaultButton.Button1);
            }


        }

        private void actualizar_Click(object sender, EventArgs e)
        {
            listaPlugins.Items.Clear();
            lista = host.actualizarPlugins();
            foreach (IPlugin p in lista)
            {
                listaPlugins.Items.Add(p.Name);
            }
            if (listaPlugins.Items.Count != 0)
                listaPlugins.SelectedIndex = 0;
        }

        private void listaPlugins_SelectedIndexChanged(object sender, EventArgs e)
        {
            descripcion.Text = lista.ElementAt(listaPlugins.SelectedIndex).Descripcion;
        }
    }
}
