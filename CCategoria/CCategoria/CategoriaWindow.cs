using System;
using System.Data;
namespace CCategoria
{
    public partial class CategoriaWindow : Gtk.Window
    {
        public CategoriaWindow() :
                base(Gtk.WindowType.Toplevel)
        {
            this.Build();

            saveAction.Activated += delegate {
                string nombre = entryNombre.Text;
				IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
                dbCommand.CommandText = "insert into categoria (nombre) values (@nombre)";
                IDbDataParameter dbDataParameter = dbCommand.CreateParameter();
                dbDataParameter.ParameterName = "nombre";
                dbDataParameter.Value = nombre;
                dbCommand.Parameters.Add(dbDataParameter);
                dbCommand.ExecuteNonQuery();

                Destroy();
            };
        }
    }
}
