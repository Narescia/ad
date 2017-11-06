using System;
using Gtk;
using MySql.Data.MySqlClient;
using System.Data;
using Serpis.Ad;

using CCategoria;

public partial class MainWindow : Gtk.Window {

    public MainWindow() : base(Gtk.WindowType.Toplevel) {
        
        Build();
        Title = "Categoría";

        deleteAction.Sensitive = false;
        editAction.Sensitive = false;

        App.Instance.Connection = new MySqlConnection ("server=localhost;database=dbprueba;user=root;password=sistemas");
		App.Instance.Connection.Open();

        TreeViewHelper.Fill(treeView, CategoriaDao.SelectAll);


        treeView.Selection.Changed += delegate {
            bool hasSelected = treeView.Selection.CountSelectedRows() > 0;
            deleteAction.Sensitive = hasSelected;
            editAction.Sensitive = hasSelected;
        };

		newAction.Activated += delegate {
            Categoria categoria = new Categoria();
			new CategoriaWindow(categoria);
			
		};

        editAction.Activated += delegate {
			object id = getId();
            Categoria categoria = CategoriaDao.Load(id);
			new CategoriaWindow(categoria);
        };

        refreshAction.Activated += delegate {
            TreeViewHelper.Fill(treeView, "select * from categoria order by id");
        };

        deleteAction.Activated += delegate {
            if (WindowHelper.Confirm(this, "¿Quieres eliminar el registro?")) {
                object id = getId();
                CategoriaDao.Delete(id);
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

    protected void OnDeleteEvent(object sender, DeleteEventArgs a) {
        App.Instance.Connection.Close();
        Application.Quit();
        a.RetVal = true;
    }

}
