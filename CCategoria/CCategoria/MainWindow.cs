using System;
using Gtk;
using MySql.Data.MySqlClient;
using System.Data;

using CCategoria;

public partial class MainWindow : Gtk.Window {

    public MainWindow() : base(Gtk.WindowType.Toplevel) {
        
        Build();
        Title = "Categoría";

        deleteAction.Sensitive = false;

        App.Instance.Connection = new MySqlConnection ("server=localhost;database=dbprueba;user=root;password=sistemas");
		App.Instance.Connection.Open();

        treeView.AppendColumn("id", new CellRendererText(), "text", 0);
        treeView.AppendColumn("nombre", new CellRendererText(), "text", 1);
		ListStore listStore = new ListStore(typeof(string), typeof(string));
        treeView.Model = listStore;

        fillListStore(listStore);

        treeView.Selection.Changed += delegate {
            bool hasSelected = treeView.Selection.CountSelectedRows() > 0;
            deleteAction.Sensitive = hasSelected;	
        };

		newAction.Activated += delegate {
			new CategoriaWindow();
			
		};

        refreshAction.Activated += delegate {
            listStore.Clear();
            fillListStore(listStore);
        };

        deleteAction.Activated += delegate {
            if (WindowHelper.Confirm(this, "¿Quieres eliminar el registro?")) {
                delete();
            }
        };
		
    }

    private void fillListStore(ListStore listStore) {
		IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
		dbCommand.CommandText = "select * from categoria order by id";
		IDataReader dataReader = dbCommand.ExecuteReader();
		while (dataReader.Read())
			listStore.AppendValues(dataReader["id"].ToString(), dataReader["nombre"]);
		dataReader.Close();
    }

    private object getId() {
		TreeIter treeIter;
		treeView.Selection.GetSelected(out treeIter);
        return treeView.Model.GetValue(treeIter, 0);

    }
    private void delete() {
        object id = getId();
		IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
		dbCommand.CommandText = "delete from categoria where id = @id";
		DbCommandHelper.AddParameter(dbCommand, "id", id);
		dbCommand.ExecuteNonQuery();
    }

	private void update() {
		/*string nombre = entryNombre.Text;
		IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
		dbCommand.CommandText = "update from categoria set nombre = @nombre where id = @id";
		DbCommandHelper.AddParameter(dbCommand, "nombre", nombre);
		dbCommand.ExecuteNonQuery();*/
	}

    protected void OnDeleteEvent(object sender, DeleteEventArgs a) {
        App.Instance.Connection.Close();
        Application.Quit();
        a.RetVal = true;
    }

}
