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
            object id = TreeViewHelper.GetId(treeView);
            Categoria categoria = CategoriaDao.Load(id);
			new CategoriaWindow(categoria);
        };

        refreshAction.Activated += delegate {
            TreeViewHelper.Fill(treeView, CategoriaDao.SelectAll);
        };

        deleteAction.Activated += delegate {
            if (WindowHelper.Confirm(this, "¿Quieres eliminar el registro?")) {
                object id = TreeViewHelper.GetId(treeView);
                CategoriaDao.Delete(id);
            }
        };
    }

    protected void OnDeleteEvent(object sender, DeleteEventArgs a) {
        App.Instance.Connection.Close();
        Application.Quit();
        a.RetVal = true;
    }

}
