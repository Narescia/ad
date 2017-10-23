using System;
using System.Data;
using Serpis.Ad;
namespace CCategoria {
    public partial class CategoriaWindow : Gtk.Window {
        public CategoriaWindow() :
                base(Gtk.WindowType.Toplevel) {
            
            this.Build();

            saveAction.Activated += delegate {
                string nombre = entryNombre.Text;
				IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
                dbCommand.CommandText = "insert into categoria (nombre) values (@nombre)";
                DbCommandHelper.AddParameter(dbCommand, "nombre", nombre);
                dbCommand.ExecuteNonQuery();

                Destroy();
            };
        }

    }
}
