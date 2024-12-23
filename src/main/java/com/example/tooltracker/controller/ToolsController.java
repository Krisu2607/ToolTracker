package com.example.tooltracker.controller;

import com.example.tooltracker.controller.newtoolcontrollers.*;
import com.example.tooltracker.database.*;
import com.example.tooltracker.model.*;
import com.example.tooltracker.model.tools.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ToolsController {








    @FXML
    private MenuButton actionsMenuButton;
    @FXML
    private Button newInsertBtn;
    @FXML
    private Button dressedBtn;
    @FXML
    private Button editTool;
    @FXML
    private Button editInsert;
    @FXML
    private Button addInsertsToPart;
    @FXML
    private Button addPartNum;


    @FXML
    private TableView<ToolInsert> insertTable;
    @FXML
    private TableColumn<ToolInsert, String> nameColumn2;
    @FXML
    private TableColumn<ToolInsert, String> indexColumn2;
    @FXML
    private TableColumn<ToolInsert, String> typeColumn2;
    @FXML
    private TableColumn<ToolInsert, Integer> qtyColumn2;
    @FXML
    private TableColumn<ToolInsert, String> infoColumn2;
    @FXML
    TableColumn<ToolInsert, String> matchingTools;
    @FXML
    private TableColumn<ToolInsert, String> addCell;

    @FXML
    private TableColumn<ToolInsert, String> insertcomment;





    @FXML
    private TableView<Tool> toolTable;

    @FXML
    ImageView EmMetimageView;

    @FXML
    ImageView DrillVHMimageView;

    @FXML
    ImageView DrillHSSimageView;



    @FXML
    private TableView<EmMet> EmMetTable;
    @FXML
    private TableColumn<Tool1, String> EmMetRemImg;
    @FXML
    private TableColumn<Tool1, String> EmMetProdColumn;
    @FXML
    private TableColumn<Tool1, String> EmMetinfoColumn;
    @FXML
    private TableColumn<EmMet, String> EMMetnameColumn;
    @FXML
    private TableColumn<EmMet, String> EMMetindexColumn;
    @FXML
    private TableColumn<EmMet,Integer> EMMetL1;
    @FXML
    private TableColumn<EmMet,Integer> EMMetL2;
    @FXML
    private TableColumn<EmMet,Double> EMMetD1;
    @FXML
    private TableColumn<EmMet,Double> EMMetD2;
    @FXML
    private TableColumn<EmMet,Integer> EMMettooths;
    @FXML
    private TableColumn<EmMet, String> EMMetmat;
    @FXML
    private TableColumn<Tool1, ToolStatus> EMMetstatus;
    @FXML
    private TableColumn<Tool1, String> EMMetcomment;


    @FXML
    private TableView<EmAlu> EmAluTable;
    @FXML
    private TableColumn<Tool1, String> EMAluRemImg;
    @FXML
    private TableColumn<Tool1, String> EmAluProdColumn;

    @FXML
    private TableColumn<Tool1, String> EMAluinfoColumn;
    @FXML
    private TableColumn<EmAlu,String> EMAlunameColumn ;
    @FXML
    private TableColumn<EmAlu,String> EMAluindexColumn ;
    @FXML
    private TableColumn<EmAlu,Integer> EMAluL1;
    @FXML
    private TableColumn<EmAlu,Integer> EMAluL2;
    @FXML
    private TableColumn<EmAlu,Double> EMAluD1;
    @FXML
    private TableColumn<EmAlu,Double> EMAluD2;
    @FXML
    private TableColumn<EmAlu,Integer> EMAlutooths;
    @FXML
    private TableColumn<EmAlu,String> EMAlumat ;
    @FXML
    private TableColumn<Tool1,ToolStatus> EMAlustatus;
    @FXML
    private TableColumn<Tool1,String> EMAlucomment;




    @FXML
    private TableView<EmR> EmRTable;
    @FXML
    private TableColumn<Tool1, String> EmRRemImg;
    @FXML
    private TableColumn<Tool1, String> EmRinfoColumn;
    @FXML
    private TableColumn<Tool1, String> EmRProdColumn;
    @FXML
    private TableColumn<EmR,String> EmRNAME ;
    @FXML
    private TableColumn<EmR,String> EmRIndex ;
    @FXML
    private TableColumn<EmR,Integer> EmRL1;
    @FXML
    private TableColumn<EmR,Integer> EmRL2;
    @FXML
    private TableColumn<EmR,Double> EmRD1;
    @FXML
    private TableColumn<EmR,Double> EmRD2;

    @FXML
    private TableColumn<EmR,Double> EmRR;
    @FXML
    private TableColumn<EmR,Integer> EmRTooths;
    @FXML
    private TableColumn<EmR,String> EmRMat ;
    @FXML
    private TableColumn<Tool1,ToolStatus> EmRStatus;
    @FXML
    private TableColumn<Tool1,String> EmRComment;




    @FXML
    private TableView<ShellMill> ShelMillFFTable;
    @FXML
    private TableColumn<Tool1, String> shellMillFFRemImg;
    @FXML
    private TableColumn<Tool1, String> shellMillFFProdColumn;
    @FXML
    private TableColumn<Tool1, String> shellMillFFinfoColumn;
    @FXML
    private TableColumn<ShellMill,String> shellMillFFName ;
    @FXML
    private TableColumn<ShellMill,String> shellMillFFIndex ;
    @FXML
    private TableColumn<ShellMill,Double> shellMillFFDiam;
    @FXML
    private TableColumn<ShellMill,Integer> shellMillFFTooths;
    @FXML
    private TableColumn<ShellMill,String> shellMillFFInserts ;
    @FXML
    private TableColumn<Tool1,ToolStatus> shellMillFFStatus;
    @FXML
    private TableColumn<Tool1,String> shellMillFFComment;
    @FXML
    private TableColumn<ShellMill,String> shellMillFFIC;
    @FXML
    private TableColumn<ShellMill,String> shellMillFFBolt;




    @FXML
    private TableView<ShellMill> ShelMillNMTable;
    @FXML
    private TableColumn<Tool1, String> shellMillNMRemImg;
    @FXML
    private TableColumn<Tool1, String> shellMillNMProdColumn;
    @FXML
    private TableColumn<Tool1, String> shellMillNMinfoColumn;
    @FXML
    private TableColumn<ShellMill,String> shellMillNMName ;
    @FXML
    private TableColumn<ShellMill,String> shellMillNMIndex ;
    @FXML
    private TableColumn<ShellMill,Double> shellMillNMDiam;
    @FXML
    private TableColumn<ShellMill,Integer> shellMillNMTooths;
    @FXML
    private TableColumn<ShellMill,String> shellMillNMInserts ;
    @FXML
    private TableColumn<Tool1,ToolStatus> shellMillNMStatus;
    @FXML
    private TableColumn<Tool1,String> shellMillNMComment;
    @FXML
    private TableColumn<ShellMill,String> shellMillNMIC;
    @FXML
    private TableColumn<ShellMill,String> shellMillNMBolt;











    @FXML
    private TableView<DrillBlades> DrillBladeTable;
    @FXML
    private TableColumn<Tool1, String> DBRemImg;
    @FXML
    private TableColumn<Tool1, String> DBinfoColumn;
    @FXML
    private TableColumn<Tool1, String> DBProdColumn;
    @FXML
    private TableColumn<DrillBlades,String> DBName ;
    @FXML
    private TableColumn<DrillBlades,String> DBIndex ;
    @FXML
    private TableColumn<DrillBlades,Double> DBDiam;
    @FXML
    private TableColumn<DrillBlades,Integer> DBlength ;
    @FXML
    private TableColumn<DrillBlades,Integer> DBTooths;
    @FXML
    private TableColumn<DrillBlades,String> DBInserts ;
    @FXML
    private TableColumn<Tool1,ToolStatus> DBStatus;
    @FXML
    private TableColumn<Tool1,String> DBComment;





    @FXML
    private TableView<DrillHSS> DrillHSSTable;
    @FXML
    private TableColumn<Tool1, String> DRHSSRemImg;
    @FXML
    private TableColumn<Tool1, String> DRHSSinfoColumn;
    @FXML
    private TableColumn<Tool1, String> DRHSSProdColumn;
    @FXML
    private TableColumn<DrillHSS,String> DRHSSName ;
    @FXML
    private TableColumn<DrillHSS,String> DRHSSIndex ;
    @FXML
    private TableColumn<DrillHSS,Double> DRHSSDiam;
    @FXML
    private TableColumn<DrillHSS,Integer> DRHSSL1;
    @FXML
    private TableColumn<DrillHSS,Integer> DRHSSQty;
    @FXML
    private TableColumn<DrillHSS,Integer> DRHSSL2;
    @FXML
    private TableColumn<Tool1,ToolStatus> DRHSSStatus;
    @FXML
    private TableColumn<Tool1,String> DRHSSComment;




    @FXML
    private TableView<DrillVHM> DrillVHMTable;
    @FXML
    private TableColumn<Tool1, String> DRVHMRemImg;
    @FXML
    private TableColumn<Tool1, String> DRVHMinfoColumn;
    @FXML
    private TableColumn<Tool1, String> DRVHMProdColumn;
    @FXML
    private TableColumn<DrillVHM,String> DRVHMName ;
    @FXML
    private TableColumn<DrillVHM,String> DRVHMIndex ;
    @FXML
    private TableColumn<DrillVHM,Double> DRVHMDiam;
    @FXML
    private TableColumn<DrillVHM,Integer> DRVHML1;
    @FXML
    private TableColumn<DrillVHM,Integer> DRVHML2;
    @FXML
    private TableColumn<Tool1,ToolStatus> DRVHMStatus;
    @FXML
    private TableColumn<Tool1,String> DRVHMComment;
    @FXML
    private TableColumn<DrillVHM,Boolean> DRVHMIC;




    @FXML
    private TableView<TapSK> TapSKTable;
    @FXML
    private TableColumn<Tool1, String> TapSKRemImg;
    @FXML
    private TableColumn<Tool1, String> TapSKinfoColumn;
    @FXML
    private TableColumn<Tool1, String> TapSKProdColumn;

    @FXML
    private TableColumn<TapSK,String> TapSKName ;
    @FXML
    private TableColumn<TapSK,String> TapSKIndex ;
    @FXML
    private TableColumn<TapSK,String> TapSKMat ;
    @FXML
    private TableColumn<TapSK,String> TapSKClass ;
    @FXML
    private TableColumn<TapSK,Double> TapSKMetricSize;
    @FXML
    private TableColumn<TapSK,Double> TapSKScroll;

    @FXML
    private TableColumn<Tool1,ToolStatus> TapSKStatus;
    @FXML
    private TableColumn<Tool1,String> TapSKComment;



    @FXML
    private TableView<TapPR> TapPRTable;
    @FXML
    private TableColumn<Tool1, String> TapPRRemImg;
    @FXML
    private TableColumn<Tool1, String> TapPRProdColumn;
    @FXML
    private TableColumn<Tool1, String> TapPRinfoColumn;
    @FXML
    private TableColumn<TapPR,String> TapPRName ;
    @FXML
    private TableColumn<TapPR,String> TapPRIndex ;
    @FXML
    private TableColumn<TapPR,String> TapPRMat ;
    @FXML
    private TableColumn<TapPR,String> TapPRClass;
    @FXML
    private TableColumn<TapPR,Double> TapPRMetricSize;
    @FXML
    private TableColumn<TapPR,Double> TapPRScroll;
    @FXML
    private TableColumn<Tool1,ToolStatus> TapPRStatus ;
    @FXML
    private TableColumn<Tool1,String> TapPRComment;




    @FXML
    private TableView<TapInch> TapInchTable;
    @FXML
    private TableColumn<Tool1, String> TapInchRemImg;
    @FXML
    private TableColumn<Tool1, String> TapInchinfoColumn;
    @FXML
    private TableColumn<Tool1, String> TapInchProdColumn;
    @FXML
    private TableColumn<TapInch,String> TapInchName ;
    @FXML
    private TableColumn<TapInch,String> TapInchIndex ;
    @FXML
    private TableColumn<TapInch,String> TapInchMat ;
    @FXML
    private TableColumn<Tool1,ToolStatus> TapInchStatus ;
    @FXML
    private TableColumn<Tool1,String> TapInchComment;

    @FXML
    private TableColumn<TapInch,String> TapInchSize;




    @FXML
    private TableView<Chamfer> ChamferTable;
    @FXML
    private TableColumn<Tool1, String> ChamRemImg;
    @FXML
    private TableColumn<Tool1, String> ChaminfoColumn;
    @FXML
    private TableColumn<Tool1, String> ChamProdColumn;
    @FXML
    private TableColumn<Chamfer,String> ChamName ;
    @FXML
    private TableColumn<Chamfer,String> ChamIndex ;
    @FXML
    private TableColumn<Chamfer,String> ChamMat ;
    @FXML
    private TableColumn<Tool1,ToolStatus> ChamStatus ;
    @FXML
    private TableColumn<Tool1,String> ChamComment;
    @FXML
    private TableColumn<Chamfer, Double> ChamD1;
    @FXML
    private TableColumn<Chamfer, Double> ChamD2;
    @FXML
    private TableColumn<Chamfer, Double> ChamAngle;
    @FXML
    private TableColumn<Chamfer, Integer> ChamL1;
    @FXML
    private TableColumn<Chamfer, Integer> ChamL2;




    @FXML
    private TableView<SpotDrill> SpotDRtable;
    @FXML
    private TableColumn<Tool1, String> SDRemImg;
    @FXML
    private TableColumn<Tool1, String> SDinfoColumn;
    @FXML
    private TableColumn<Tool1, String> SDProdColumn;
    @FXML
    private TableColumn<SpotDrill,String> SDName ;
    @FXML
    private TableColumn<SpotDrill,String> SDIndex ;
    @FXML
    private TableColumn<SpotDrill,Integer> SDL1;
    @FXML
    private TableColumn<SpotDrill,Double> SDD1;
    @FXML
    private TableColumn<SpotDrill,Double> SDD2;
    @FXML
    private TableColumn<SpotDrill,String> SDMat ;
    @FXML
    private TableColumn<Tool1,ToolStatus> SDStatus;
    @FXML
    private TableColumn<Tool1,String> SDComment;



    @FXML
    private TableView<Reamer> ReamerTable;
    @FXML
    private TableColumn<Tool1, String> ReamerRemImg;
    @FXML
    private TableColumn<Tool1, String> ReamerinfoColumn;
    @FXML
    private TableColumn<Tool1, String> ReamerProdColumn;
    @FXML
    private TableColumn<Reamer,String> ReamerName ;
    @FXML
    private TableColumn<Reamer,String> ReamerIndex ;
    @FXML
    private TableColumn<Reamer,Integer> ReamerL1;
    @FXML
    private TableColumn<Reamer,Double> ReamerDiam;
    @FXML
    private TableColumn<Reamer,String> ReamerMat ;
    @FXML
    private TableColumn<Tool1,ToolStatus> ReamerStatus;
    @FXML
    private TableColumn<Tool1,String> ReamerComment;






    @FXML
    private TableView<VariousTool> VariousTable;
    @FXML
    private TableColumn<Tool1, String> VariousRemImg;
    @FXML
    private TableColumn<Tool1, String> VariousProdColumn;
    @FXML
    private TableColumn<Tool1, String> VariousinfoColumn;
    @FXML
    private TableColumn<VariousTool,String> VariousName ;
    @FXML
    private TableColumn<VariousTool,String> VariousIndex ;

    @FXML
    private TableColumn<VariousTool,Double> VariousDiam;
    @FXML
    private TableColumn<VariousTool,String> VariousType ;
    @FXML
    private TableColumn<Tool1,ToolStatus> VariousStatus;
    @FXML
    private TableColumn<Tool1,String> VariousComment;



    @FXML
    private TableView<ThreadDie> DieTable;
    @FXML
    private TableColumn<Tool1, String> DieRemImg;
    @FXML
    private TableColumn<Tool1, String> DieinfoColumn;
    @FXML
    private TableColumn<Tool1, String> DieProdColumn;
    @FXML
    private TableColumn<ThreadDie,String> DieName ;
    @FXML
    private TableColumn<ThreadDie,String> DieIndex ;
    @FXML
    private TableColumn<ThreadDie,String> DieMat ;
    @FXML
    private TableColumn<ThreadDie,String> DieClass;
    @FXML
    private TableColumn<ThreadDie,Double> DieMetricSize;
    @FXML
    private TableColumn<ThreadDie,Double> DieInchSize;
    @FXML
    private TableColumn<Tool1,ToolStatus> DieStatus ;
    @FXML
    private TableColumn<Tool1,String> DieComment;
    @FXML
    private TableColumn<ThreadDie,Double> DieScroll;




    @FXML
    private TableView<TurningOD> OdLatheTable;
    @FXML
    private TableColumn<Tool1, String> ODRemImg;
    @FXML
    private TableColumn<Tool1, String> ODinfoColumn;
    @FXML
    private TableColumn<Tool1, String> ODProdColumn;
    @FXML
    private TableColumn<Tool1, String> ODBolt;
    @FXML
    private TableColumn<TurningOD,String> ODName ;
    @FXML
    private TableColumn<TurningOD,String> ODIndex ;
    @FXML
    private TableColumn<TurningOD,String> ODInserts ;
    @FXML
    private TableColumn<TurningOD,String> ODType ;
    @FXML
    private TableColumn<Tool1,ToolStatus> ODStatus;
    @FXML
    private TableColumn<Tool1,String> ODComment;



    @FXML
    private TableView<TurningID> IdLatheTable;
    @FXML
    private TableColumn<Tool1, String> IDRemImg;
    @FXML
    private TableColumn<Tool1, String> IDinfoColumn;
    @FXML
    private TableColumn<Tool1, String> IDProdColumn;
    @FXML
    private TableColumn<Tool1, String> IDBolt;
    @FXML
    private TableColumn<TurningID,String> IDName ;
    @FXML
    private TableColumn<TurningID,String> IDIndex ;
    @FXML
    private TableColumn<TurningID,String> IDInserts ;
    @FXML
    private TableColumn<TurningID,String> IDType ;
    @FXML
    private TableColumn<Tool1,ToolStatus> IDStatus;
    @FXML
    private TableColumn<Tool1,String> IDComment;



    @FXML
    private TableView<FaceGroove> FaceGrooveTable;
    @FXML
    private TableColumn<Tool1, String> FaceGrooveRemImg;
    @FXML
    private TableColumn<Tool1, String> FaceGrooveProdColumn;
    @FXML
    private TableColumn<Tool1, String> FaceGrooveinfoColumn;
    @FXML
    private TableColumn<FaceGroove,String> FaceGrooveName ;
    @FXML
    private TableColumn<FaceGroove,String> FaceGrooveIndex ;
    @FXML
    private TableColumn<FaceGroove,String> FaceGrooveInserts ;
    @FXML
    private TableColumn<Tool1,ToolStatus> FaceGrooveStatus;
    @FXML
    private TableColumn<Tool1,String> FaceGrooveComment;
    @FXML
    private TableColumn<FaceGroove,Double> FaceGrooveMinDiam;
    @FXML
    private TableColumn<FaceGroove,Double> FaceGrooveMaxDiam;
    @FXML
    private TableColumn<FaceGroove,Integer> FaceGrooveMaxDepth;
    @FXML
    private TableColumn<FaceGroove,String> FaceGrooveBolt;










    @FXML TextField toolSearch;


    private ToolDAO toolDAO = new ToolDAO();

    private ToolInsertDAO toolInsertDAO = new ToolInsertDAO();
    private EmMetDAO emMetDAO = new EmMetDAO();
    private EMRDAO emRDAO = new EMRDAO();
    private EmAluDAO emALUDAO = new EmAluDAO();
    private ChamferDAO chamferDAO = new ChamferDAO();
    private DrillBladesDAO drillBladesDAO = new DrillBladesDAO();
    private DrillHssDAO drillHssDAO = new DrillHssDAO();
    private DrillvhmDAO drillvhmDAO = new DrillvhmDAO();
    private ReamerDAO reamerDAO = new ReamerDAO();
    private  ShellMillDAO shellMillDAO = new ShellMillDAO();
    private SpotDrillDAO spotDrillDAO = new SpotDrillDAO();
    private TapPrDAO tapPrDAO = new TapPrDAO();
    private TapSkDAO tapSkDAO = new TapSkDAO();
    private ThreadDieDAO threadDieDAO = new ThreadDieDAO();
    private IdTurningDAO idTurningDAO = new IdTurningDAO();
    private OdTurningDAO odTurningDAO = new OdTurningDAO();
    private TapInchDAO tapInchDAO = new TapInchDAO();
    private FaceGrooveDAO grooveDAO = new FaceGrooveDAO();
    private VariousToolDAO variousToolDA0 = new VariousToolDAO();
    private ActionDAO actionDAO = new ActionDAO();
    private InsertActionDAO insertActionDAO = new InsertActionDAO();


    List<TapInch> allTapInch = tapInchDAO.getallTapInch();
    List<ToolInsert> allToolInserts = toolInsertDAO.getAllToolInserts();
    List<EmMet> allEmMet = emMetDAO.getAllEmMetTools();
    List<EmR> allEmR = emRDAO.getAllEmRTools();
    List<EmAlu> allEmAlu = emALUDAO.getAllEmAluTools();
//    List<Chamfer> allChamfers = chamferDAO.getAllChemferTools();
    List<DrillBlades> allDrillBlades = drillBladesDAO.getAllDrillBlades();
    List<DrillHSS> allHSSdrill = drillHssDAO.getAllDrillHSS();
    List<DrillVHM> allVHMdrill = drillvhmDAO.getAllDrillHSS();
//    List<Reamer> allReamers = reamerDAO.getAllReamerTools();
    List<ShellMill> allShellFFMills = shellMillDAO.getAllFFShellMill();
    List<ShellMill> allShellNMMills = shellMillDAO.getAllNMShellMill();
//    List<SpotDrill> allSpotDrills = spotDrillDAO.getAllSpotDrillTools();
    List<TapPR> allTapPr = tapPrDAO.getAllPrTap();
    List<TapSK> allTapSk = tapSkDAO.getAllSkTap();
    List<Chamfer> allChamfers = chamferDAO.getAllChemferTools();
    List<Reamer> allReamers = reamerDAO.getAllReamerTools();
    List<VariousTool> allVariousTools = variousToolDA0.getAllVariousTools();
    List<ThreadDie> allThreadDie = threadDieDAO.getAllThreadDie();
    List<SpotDrill> allSpotDrill = spotDrillDAO.getAllSpotDrillTools();
    List<TurningOD> allOdTurning = odTurningDAO.getAllOdTurnTools();
    List<TurningID> allIdTurning = idTurningDAO.getAllIdTurnTools();
    List<FaceGroove> allFaceGroove = grooveDAO.getAllFaceGrooveTools();



//    List<ThreadDie> allThreadDies = threadDieDAO.getAllThreadDie();






    public ToolsController() throws SQLException {
    }


    public void initialize() {

        Users loggedUser = LoggedUser.getUser();
        if (loggedUser.getRole() == 2) {
            actionsMenuButton.setDisable(true);
            newInsertBtn.setDisable(true);
            dressedBtn.setDisable(true);
            editTool.setDisable(true);
            editInsert.setDisable(true);
            addInsertsToPart.setDisable(true);
            addPartNum.setDisable(true);
        }







        insertcomment.setCellFactory(TextFieldTableCell.forTableColumn());

        // Dodaj listener do aktualizacji wartości w bazie danych po zakończeniu edycji
        insertcomment.setOnEditCommit(event -> {
            ToolInsert toolInsert = event.getRowValue();
            toolInsert.setComment(event.getNewValue());

            // Zaktualizuj rekord w bazie danych
            toolInsertDAO.updateToolInsertComment(toolInsert);
        });

        insertTable.setEditable(true);





        EmMetimageView.setImage(new Image(getClass().getResourceAsStream("/com/example/tooltracker/icons/toolsizesimages/emsize.png")));
        DrillVHMimageView.setImage(new Image(getClass().getResourceAsStream("/com/example/tooltracker/icons/toolsizesimages/drillsizes.png")));
        DrillHSSimageView.setImage(new Image(getClass().getResourceAsStream("/com/example/tooltracker/icons/toolsizesimages/drillsizes.png")));







        EMMetnameColumn.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        EmMetProdColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        EMMetL1.setCellValueFactory(new PropertyValueFactory<>("L1"));
        EMMetL2.setCellValueFactory(new PropertyValueFactory<>("L2"));
        EMMetD1.setCellValueFactory(new PropertyValueFactory<>("d1"));
        EMMetD2.setCellValueFactory(new PropertyValueFactory<>("d2"));
        EMMettooths.setCellValueFactory(new PropertyValueFactory<>("toothsQty"));
        EMMetmat.setCellValueFactory(new PropertyValueFactory<>("material"));
        EMMetstatus.setCellValueFactory(new PropertyValueFactory<>("toolStatus"));
        EMMetcomment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        EMMetindexColumn.setCellValueFactory(new PropertyValueFactory<>("toolIndex"));



        VariousName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        VariousProdColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        VariousDiam.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        VariousType.setCellValueFactory(new PropertyValueFactory<>("toolType"));
        VariousStatus.setCellValueFactory(new PropertyValueFactory<>("toolStatus"));
        VariousComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        VariousIndex.setCellValueFactory(new PropertyValueFactory<>("toolIndex"));






      EMAlunameColumn.setCellValueFactory(new PropertyValueFactory<>("toolName"));
      EmAluProdColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
      EMAluL1.setCellValueFactory(new PropertyValueFactory<>("L1"));
      EMAluL2.setCellValueFactory(new PropertyValueFactory<>("L2"));
      EMAluD1.setCellValueFactory(new PropertyValueFactory<>("d1"));
      EMAluD2.setCellValueFactory(new PropertyValueFactory<>("d2"));
      EMAlutooths.setCellValueFactory(new PropertyValueFactory<>("toothsQty"));
      EMAlumat.setCellValueFactory(new PropertyValueFactory<>("material"));
      EMAlustatus.setCellValueFactory(new PropertyValueFactory<>("toolStatus"));
      EmRComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
      EMAluindexColumn.setCellValueFactory(new PropertyValueFactory<>("toolIndex"));



        EmRNAME.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        EmRProdColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        EmRL1.setCellValueFactory(new PropertyValueFactory<>("L1"));
        EmRL2.setCellValueFactory(new PropertyValueFactory<>("L2"));
        EmRD1.setCellValueFactory(new PropertyValueFactory<>("d1"));
        EmRD2.setCellValueFactory(new PropertyValueFactory<>("d2"));
        EmRTooths.setCellValueFactory(new PropertyValueFactory<>("toothsQty"));
        EmRMat.setCellValueFactory(new PropertyValueFactory<>("material"));
        EmRStatus.setCellValueFactory(new PropertyValueFactory<>("toolStatus"));
        EmRComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        EmRIndex.setCellValueFactory(new PropertyValueFactory<>("toolIndex"));
        EmRR.setCellValueFactory(new PropertyValueFactory<>("cornerR"));




        shellMillFFName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        shellMillFFProdColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        shellMillFFIndex.setCellValueFactory(new PropertyValueFactory<>("toolIndex"));
        shellMillFFStatus.setCellValueFactory(new PropertyValueFactory<>("toolStatus"));
        shellMillFFComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        shellMillFFDiam.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        shellMillFFTooths.setCellValueFactory(new PropertyValueFactory<>("toothsQty"));
        shellMillFFInserts.setCellValueFactory(new PropertyValueFactory<>("matchingInserts"));
        shellMillFFIC.setCellValueFactory(new PropertyValueFactory<>("isItIc"));
        shellMillFFBolt.setCellValueFactory(new PropertyValueFactory<>("matchingBolt"));

        shellMillNMName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        shellMillNMProdColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        shellMillNMIndex.setCellValueFactory(new PropertyValueFactory<>("toolIndex"));
        shellMillNMStatus.setCellValueFactory(new PropertyValueFactory<>("toolStatus"));
        shellMillNMComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        shellMillNMDiam.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        shellMillNMTooths.setCellValueFactory(new PropertyValueFactory<>("toothsQty"));
        shellMillNMInserts.setCellValueFactory(new PropertyValueFactory<>("matchingInserts"));
        shellMillNMIC.setCellValueFactory(new PropertyValueFactory<>("isItIc"));
        shellMillNMBolt.setCellValueFactory(new PropertyValueFactory<>("matchingBolt"));



        DBName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        DBProdColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        DBIndex.setCellValueFactory(new PropertyValueFactory<>("toolIndex"));
        DBStatus.setCellValueFactory(new PropertyValueFactory<>("toolStatus"));
        DBComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        DBDiam.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        DBTooths.setCellValueFactory(new PropertyValueFactory<>("toothsQty"));
        DBInserts.setCellValueFactory(new PropertyValueFactory<>("matchingInserts"));
        DBlength.setCellValueFactory(new PropertyValueFactory<>("length"));

        DRHSSName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        DRHSSProdColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        DRHSSIndex.setCellValueFactory(new PropertyValueFactory<>("toolIndex"));
        DRHSSStatus.setCellValueFactory(new PropertyValueFactory<>("toolStatus"));
        DRHSSComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        DRHSSDiam.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        DRHSSL1.setCellValueFactory(new PropertyValueFactory<>("length"));
        DRHSSL2.setCellValueFactory(new PropertyValueFactory<>("workLength"));
        DRHSSQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        DRVHMName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        DRVHMProdColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        DRVHMIndex.setCellValueFactory(new PropertyValueFactory<>("toolIndex"));
        DRVHMStatus.setCellValueFactory(new PropertyValueFactory<>("toolStatus"));
        DRVHMComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        DRVHMDiam.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        DRVHML1.setCellValueFactory(new PropertyValueFactory<>("length"));
        DRVHML2.setCellValueFactory(new PropertyValueFactory<>("workLength"));
        DRVHMIC.setCellValueFactory(new PropertyValueFactory<>("isInternalCooled"));

        TapSKName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        TapSKProdColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        TapSKIndex.setCellValueFactory(new PropertyValueFactory<>("toolIndex"));
        TapSKStatus.setCellValueFactory(new PropertyValueFactory<>("toolStatus"));
        TapSKComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        TapSKMat.setCellValueFactory(new PropertyValueFactory<>("materialType"));
        TapSKClass.setCellValueFactory(new PropertyValueFactory<>("threadClass"));
        TapSKMetricSize.setCellValueFactory(new PropertyValueFactory<>("metricSize"));
        TapSKScroll.setCellValueFactory(new PropertyValueFactory<>("tapScroll"));


        TapPRName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        TapPRProdColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        TapPRIndex.setCellValueFactory(new PropertyValueFactory<>("toolIndex"));
        TapPRStatus.setCellValueFactory(new PropertyValueFactory<>("toolStatus"));
        TapPRComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        TapPRMat.setCellValueFactory(new PropertyValueFactory<>("materialType"));
        TapPRClass.setCellValueFactory(new PropertyValueFactory<>("threadClass"));
        TapPRMetricSize.setCellValueFactory(new PropertyValueFactory<>("metricSize"));
        TapPRScroll.setCellValueFactory(new PropertyValueFactory<>("tapScroll"));



        TapInchName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        TapInchProdColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        TapInchIndex.setCellValueFactory(new PropertyValueFactory<>("toolIndex"));
        TapInchStatus.setCellValueFactory(new PropertyValueFactory<>("toolStatus"));
        TapInchComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        TapInchMat.setCellValueFactory(new PropertyValueFactory<>("materialType"));
        TapInchSize.setCellValueFactory(new PropertyValueFactory<>("inchSize"));

        ChamName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        ChamProdColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        ChamIndex.setCellValueFactory(new PropertyValueFactory<>("toolIndex"));
        ChamStatus.setCellValueFactory(new PropertyValueFactory<>("toolStatus"));
        ChamComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        ChamMat.setCellValueFactory(new PropertyValueFactory<>("materialType"));
        ChamD1.setCellValueFactory(new PropertyValueFactory<>("d1"));
        ChamD2.setCellValueFactory(new PropertyValueFactory<>("d2"));
        ChamL1.setCellValueFactory(new PropertyValueFactory<>("l1"));
        ChamL2.setCellValueFactory(new PropertyValueFactory<>("l2"));
        ChamAngle.setCellValueFactory(new PropertyValueFactory<>("angle"));



        SDName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        SDProdColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        SDIndex.setCellValueFactory(new PropertyValueFactory<>("toolIndex"));
        SDStatus.setCellValueFactory(new PropertyValueFactory<>("toolStatus"));
        SDComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        SDMat.setCellValueFactory(new PropertyValueFactory<>("materialType"));
        SDD1.setCellValueFactory(new PropertyValueFactory<>("d1"));
        SDD2.setCellValueFactory(new PropertyValueFactory<>("d2"));
        SDL1.setCellValueFactory(new PropertyValueFactory<>("length"));


        ReamerName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        ReamerProdColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        ReamerIndex.setCellValueFactory(new PropertyValueFactory<>("toolIndex"));
        ReamerStatus.setCellValueFactory(new PropertyValueFactory<>("toolStatus"));
        ReamerComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        ReamerMat.setCellValueFactory(new PropertyValueFactory<>("material"));
        ReamerDiam.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        ReamerL1.setCellValueFactory(new PropertyValueFactory<>("length"));


        DieName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        DieProdColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        DieIndex.setCellValueFactory(new PropertyValueFactory<>("toolIndex"));
        DieStatus.setCellValueFactory(new PropertyValueFactory<>("toolStatus"));
        DieComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        DieMat.setCellValueFactory(new PropertyValueFactory<>("materialType"));
        DieClass.setCellValueFactory(new PropertyValueFactory<>("threadClass"));
        DieMetricSize.setCellValueFactory(new PropertyValueFactory<>("metricSize"));
        DieInchSize.setCellValueFactory(new PropertyValueFactory<>("inchsize"));
        DieScroll.setCellValueFactory(new PropertyValueFactory<>("dieScroll"));

        ODName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        ODProdColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        ODIndex.setCellValueFactory(new PropertyValueFactory<>("toolIndex"));
        ODStatus.setCellValueFactory(new PropertyValueFactory<>("toolStatus"));
        ODComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        ODType.setCellValueFactory(new PropertyValueFactory<>("workType"));
        ODInserts.setCellValueFactory(new PropertyValueFactory<>("matchingInserts"));
        ODBolt.setCellValueFactory(new PropertyValueFactory<>("matchingBolt"));


        IDName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        IDProdColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        IDIndex.setCellValueFactory(new PropertyValueFactory<>("toolIndex"));
        IDStatus.setCellValueFactory(new PropertyValueFactory<>("toolStatus"));
        IDComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        IDType.setCellValueFactory(new PropertyValueFactory<>("workType"));
        IDInserts.setCellValueFactory(new PropertyValueFactory<>("matchingInserts"));
        IDBolt.setCellValueFactory(new PropertyValueFactory<>("matchingBolt"));


        FaceGrooveName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        FaceGrooveProdColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        FaceGrooveIndex.setCellValueFactory(new PropertyValueFactory<>("toolIndex"));
        FaceGrooveStatus.setCellValueFactory(new PropertyValueFactory<>("toolStatus"));
        FaceGrooveComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        FaceGrooveInserts.setCellValueFactory(new PropertyValueFactory<>("matchingInserts"));
        FaceGrooveMinDiam.setCellValueFactory(new PropertyValueFactory<>("minDiamCut"));
        FaceGrooveMaxDiam.setCellValueFactory(new PropertyValueFactory<>("maxDiamCut"));
        FaceGrooveMaxDepth.setCellValueFactory(new PropertyValueFactory<>("maxDepth"));
        FaceGrooveBolt.setCellValueFactory(new PropertyValueFactory<>("matchingBolt"));












//
//
//
//
//
//        // Inicjalizacja kolumn w Płytkach
//
        nameColumn2.setCellValueFactory(new PropertyValueFactory<>("insertName"));
        indexColumn2.setCellValueFactory(new PropertyValueFactory<>("insertIndex"));
        typeColumn2.setCellValueFactory(new PropertyValueFactory<>("insertType"));
        qtyColumn2.setCellValueFactory(new PropertyValueFactory<>("insertQty"));
        insertcomment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        matchingTools.setCellValueFactory(new PropertyValueFactory<>("toolsMatch"));

        qtyColumn2.setCellFactory(setColourInInsertUsage);





        // Pobierz wszystkie narzędzia z bazy danych
//        List<Tool> allTools = toolDAO.getAllTools();
//        allTools.stream()
//                .forEach(t -> t.setInsertQty(getMatchingInsertQty(t)));
//
//
//
//        // Tworzymy ObservableList i przypisujemy do TableView
//        FilteredList<Tool> filteredToolData = getFilteredToolData(allTools);
////        toolTable.setItems(filteredToolData);
//
        // Pobierz wsyztskie płytki z bazy danych
        ObservableList<ToolInsert> toolInserts = FXCollections.observableArrayList(allToolInserts);
        insertTable.setItems(toolInserts);
//        statusColumn.setCellFactory(setBackgroundOnStatus);
        DBinfoColumn.setCellFactory(setToolInfoBtn);
        ChaminfoColumn.setCellFactory(setToolInfoBtn);
        DRHSSinfoColumn.setCellFactory(setToolInfoBtn);
        DRVHMinfoColumn.setCellFactory(setToolInfoBtn);
        EMAluinfoColumn.setCellFactory(setToolInfoBtn);
        EmRinfoColumn.setCellFactory(setToolInfoBtn);
        EmMetinfoColumn.setCellFactory(setToolInfoBtn);
        FaceGrooveinfoColumn.setCellFactory(setToolInfoBtn);
        ReamerinfoColumn.setCellFactory(setToolInfoBtn);
        shellMillFFinfoColumn.setCellFactory(setToolInfoBtn);
        shellMillNMinfoColumn.setCellFactory(setToolInfoBtn);
        SDinfoColumn.setCellFactory(setToolInfoBtn);
        TapInchinfoColumn.setCellFactory(setToolInfoBtn);
        TapPRinfoColumn.setCellFactory(setToolInfoBtn);
        TapSKinfoColumn.setCellFactory(setToolInfoBtn);
        DieinfoColumn.setCellFactory(setToolInfoBtn);
        IDinfoColumn.setCellFactory(setToolInfoBtn);
        ODinfoColumn.setCellFactory(setToolInfoBtn);
        VariousinfoColumn.setCellFactory(setToolInfoBtn);

//
//
        DBStatus.setCellFactory(setBackgroundOnStatus);
        ChamStatus.setCellFactory(setBackgroundOnStatus);
        DRHSSStatus.setCellFactory(setBackgroundOnStatus);
        DRVHMStatus.setCellFactory(setBackgroundOnStatus);
        EMAlustatus.setCellFactory(setBackgroundOnStatus);
        EmRStatus.setCellFactory(setBackgroundOnStatus);
        EMMetstatus.setCellFactory(setBackgroundOnStatus);
        FaceGrooveStatus.setCellFactory(setBackgroundOnStatus);
        ReamerStatus.setCellFactory(setBackgroundOnStatus);
        shellMillFFStatus.setCellFactory(setBackgroundOnStatus);
        shellMillNMStatus.setCellFactory(setBackgroundOnStatus);
        SDStatus.setCellFactory(setBackgroundOnStatus);
        TapInchStatus.setCellFactory(setBackgroundOnStatus);
        TapPRStatus.setCellFactory(setBackgroundOnStatus);
        TapSKStatus.setCellFactory(setBackgroundOnStatus);
        DieStatus.setCellFactory(setBackgroundOnStatus);
        IDStatus.setCellFactory(setBackgroundOnStatus);
        ODStatus.setCellFactory(setBackgroundOnStatus);
        VariousStatus.setCellFactory(setBackgroundOnStatus);

        addCell.setCellFactory(createImageButtonForAddingInserts());








//        DBRemImg.setCellFactory(createImageButtonCellFactory("drillblades"));
//        ChamRemImg.setCellFactory(createImageButtonCellFactory("chamfer"));
//        DRHSSRemImg.setCellFactory(createImageButtonCellFactory("drillhss"));
//        DRVHMRemImg.setCellFactory(createImageButtonCellFactory("drillvhm"));
//        EMAluRemImg.setCellFactory(createImageButtonCellFactory("emalu"));
//        EmRRemImg.setCellFactory(createImageButtonCellFactory("emr"));
//        EmMetRemImg.setCellFactory(createImageButtonCellFactory("emmet"));
//        FaceGrooveRemImg.setCellFactory(createImageButtonCellFactory("facegroove"));
//        ReamerRemImg.setCellFactory(createImageButtonCellFactory("reamer"));
//        shellMillFFRemImg.setCellFactory(createImageButtonCellFactory("shellmill"));
//        shellMillNMRemImg.setCellFactory(createImageButtonCellFactory("shellmill"));
//        SDRemImg.setCellFactory(createImageButtonCellFactory("spotdrill"));
//        TapInchRemImg.setCellFactory(createImageButtonCellFactory("tapinch"));
//        TapSKRemImg.setCellFactory(createImageButtonCellFactory("tapsk"));
//        TapPRRemImg.setCellFactory(createImageButtonCellFactory("tappr"));
//        DieRemImg.setCellFactory(createImageButtonCellFactory("threaddie"));
//        IDRemImg.setCellFactory(createImageButtonCellFactory("turningid"));
//        ODRemImg.setCellFactory(createImageButtonCellFactory("turningod"));
//        VariousRemImg.setCellFactory(createImageButtonCellFactory("variuoustool"));






//        infoColumn2.setCellFactory(setParamBtn);
//
//        typeColumn2.setCellFactory(setInsertImage);





        ObservableList<EmMet> EmMetOL = FXCollections.observableArrayList(allEmMet);
        EmMetTable.setItems(EmMetOL);
        ObservableList<EmAlu> EmAluOL = FXCollections.observableArrayList(allEmAlu);
        EmAluTable.setItems(EmAluOL);
        ObservableList<EmR> EmROL = FXCollections.observableArrayList(allEmR);
        EmRTable.setItems(EmROL);

        ObservableList<DrillHSS> drHSSOL = FXCollections.observableArrayList(allHSSdrill);
        DrillHSSTable.setItems(drHSSOL);
        ObservableList<DrillVHM> drVHMOL = FXCollections.observableArrayList(allVHMdrill);
        DrillVHMTable.setItems(drVHMOL);

        ObservableList<DrillBlades> drillBladesOL = FXCollections.observableArrayList(allDrillBlades);
        DrillBladeTable.setItems(drillBladesOL);

        ObservableList<Chamfer> chamfOL = FXCollections.observableArrayList(allChamfers);
        ChamferTable.setItems(chamfOL);

        ObservableList<Reamer> reamerOL = FXCollections.observableArrayList(allReamers);
        ReamerTable.setItems(reamerOL);

        ObservableList<ShellMill> shellmilFFlOL = FXCollections.observableArrayList(allShellFFMills);
        ShelMillFFTable.setItems(shellmilFFlOL);

        ObservableList<ShellMill> shellmilNMlOL = FXCollections.observableArrayList(allShellNMMills);
        ShelMillNMTable.setItems(shellmilNMlOL);

        ObservableList<TapPR> tapprOL = FXCollections.observableArrayList(allTapPr);
        TapPRTable.setItems(tapprOL);

        ObservableList<TapSK> tapskOL = FXCollections.observableArrayList(allTapSk);
        TapSKTable.setItems(tapskOL);

        ObservableList<TapInch> tapInchOL = FXCollections.observableArrayList(allTapInch);
        TapInchTable.setItems(tapInchOL);

        ObservableList<SpotDrill> spotDrillOL = FXCollections.observableArrayList(allSpotDrill);
        SpotDRtable.setItems(spotDrillOL);

        ObservableList<ThreadDie> threadDieOL = FXCollections.observableArrayList(allThreadDie);
        DieTable.setItems(threadDieOL);

        ObservableList<TurningOD> odTurnOL = FXCollections.observableArrayList(allOdTurning);
        OdLatheTable.setItems(odTurnOL);

        ObservableList<TurningID> idTurnOL = FXCollections.observableArrayList(allIdTurning);
        IdLatheTable.setItems(idTurnOL);

        ObservableList<FaceGroove> faceGroovesOL = FXCollections.observableArrayList(allFaceGroove);
        FaceGrooveTable.setItems(faceGroovesOL);



        ObservableList<VariousTool> variousTools = FXCollections.observableArrayList(allVariousTools);
        VariousTable.setItems(variousTools);

        configureCommentColumn("chamfer", ChamComment, ChamferTable, 250);
        configureCommentColumn("drillblades", DBComment, DrillBladeTable, 250);
        configureCommentColumn("drillhss", DRHSSComment, DrillHSSTable, 250);
        configureCommentColumn("drillvhm", DRVHMComment, DrillVHMTable, 250);
        configureCommentColumn("threaddie", DieComment, DieTable, 250);
        configureCommentColumn("tapsk", TapSKComment, TapSKTable, 250);
        configureCommentColumn("tappr", TapPRComment, TapPRTable, 250);
        configureCommentColumn("tapinch", TapInchComment, TapInchTable, 250);
        configureCommentColumn("turningod", IDComment, OdLatheTable, 250);
        configureCommentColumn("turningid", ODComment, IdLatheTable, 250);
        configureCommentColumn("facegroove", FaceGrooveComment, FaceGrooveTable, 250);
        configureCommentColumn("reamer", ReamerComment, ReamerTable, 250);
        configureCommentColumn("shellmill", shellMillFFComment, ShelMillFFTable, 250);
        configureCommentColumn("shellmill", shellMillNMComment, ShelMillNMTable, 250);
        configureCommentColumn("spotdrill", SDComment, SpotDRtable, 250);
        configureCommentColumn("emalu", EMAlucomment, EmAluTable, 250);
        configureCommentColumn("emmet", EMMetcomment, EmMetTable, 250);
        configureCommentColumn("emr", EmRComment, EmRTable, 250);
        configureCommentColumn("variuoustools", VariousComment, VariousTable, 250);






    }



    public <S> void configureCommentColumn(String dbName, TableColumn<Tool1, String> commentColumn, TableView<S> tabView, int maxLength) {
        Tool1Dao tool1DAO = getToolDAO(dbName);

        tabView.getSelectionModel().setCellSelectionEnabled(true);
        tabView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tabView.setOnMouseClicked(this::handleMousePressed);
        tabView.setEditable(true);
        commentColumn.setEditable(true);
        commentColumn.setCellFactory(com.example.tooltracker.controller.LimitedTextFieldTableCell.forTableColumn(maxLength));



        commentColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Tool1, String> t) -> {
                    Tool1 tool1 = t.getTableView().getItems().get(t.getTablePosition().getRow());
                    tool1.setComment(t.getNewValue()); // Aktualizacja pola 'comment'

                    // Pobierz wartość z kolumny "index"
                    String toolIndex = t.getTableView().getItems().get(t.getTablePosition().getRow()).getToolIndex();
                    tool1.setToolIndex(toolIndex);

                    tool1DAO.updateComment(tool1);
                }
        );
    }








    public Tool1Dao getToolDAO(String dbName) {
        switch (dbName.toLowerCase()) {
            case "chamfer" :
                return new ChamferDAO();
            case "drillblades":
                return new DrillBladesDAO();
            case "drillhss":
                return new DrillHssDAO();
            case "drillvhm":
                return new DrillvhmDAO();
            case "emalu":
                return new EmAluDAO();
            case "emmet":
                return new EmMetDAO();
            case "emr":
                return new EMRDAO();
            case "facegroove":
                return new FaceGrooveDAO();
            case "turningid":
                return new IdTurningDAO();
            case "turningod":
                return new OdTurningDAO();
            case "reamer":
                return new ReamerDAO();
            case "shellmill":
                return new ShellMillDAO();
            case "spotdrill":
                return new SpotDrillDAO();
            case "tapinch":
                return new TapInchDAO();
            case "tappr":
                return new TapPrDAO();
            case "tapsk":
                return new TapSkDAO();
            case "threaddie":
                return new ThreadDieDAO();
            case "variuoustools":
                return new VariousToolDAO();


            // Dodaj inne przypadki dla różnych baz danych
            default:
                throw new IllegalArgumentException("Unsupported database: " + dbName);
        }
    }


    private void handleMousePressed(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY) {
            showContextMenu(event);
        }
    }











//
//
    private void showContextMenu(MouseEvent event) {

        // Dodaj opcję kopiowania zaznaczonego obszaru
        MenuItem item = new MenuItem("Kopiér");
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<TablePosition> posList = toolTable.getSelectionModel().getSelectedCells();
                int old_r = -1;
                StringBuilder clipboardString = new StringBuilder();
                for (TablePosition p : posList) {
                    int r = p.getRow();
                    int c = p.getColumn();
                    Object cell = toolTable.getColumns().get(c).getCellData(r);
                    if (cell == null)
                        cell = "";
                    if (old_r == r)
                        clipboardString.append('\t');
                    else if (old_r != -1)
                        clipboardString.append('\n');
                    clipboardString.append(cell);
                    old_r = r;
                }
                final ClipboardContent content = new ClipboardContent();
                content.putString(clipboardString.toString());
                Clipboard.getSystemClipboard().setContent(content);
            }
        });

        ContextMenu menu = new ContextMenu();
        menu.getItems().add(item);
        // Wyświetl kontekstowe menu w miejscu kliknięcia myszy
        menu.show(toolTable, event.getScreenX(), event.getScreenY());
    }
//
//    private void copySelectedToClipboard() {
//        // Pobierz zaznaczone wiersze
//        ObservableList<Tool> selectedItems = toolTable.getSelectionModel().getSelectedItems();
//
//        // Skopiuj dane do schowka systemowego
//        Clipboard clipboard = Clipboard.getSystemClipboard();
//        ClipboardContent content = new ClipboardContent();
//        content.putString(formatSelectedData(selectedItems));
//        clipboard.setContent(content);
//    }
//
//
//    private void selectAllRows() {
//        // Pobierz TableView
//        TableView<Tool> tableView = toolTable;
//
//        // Pobierz SelectionModel z TableView
//        TableView.TableViewSelectionModel<Tool> selectionModel = tableView.getSelectionModel();
//
//        // Zaznacz wszystkie wiersze
//        selectionModel.selectAll();
//
//        // Jeśli chcesz skopiować zaznaczone dane do schowka, możesz to zrobić tutaj
//        copySelectedToClipboard();
//    }
//
//
//    private String formatSelectedData(ObservableList<Tool> selectedItems) {
//        // Tutaj dostosuj formatowanie zaznaczonych danych do swoich potrzeb
//        StringBuilder formattedData = new StringBuilder();
//        for (Tool tool : selectedItems) {
//            formattedData.append(tool.getToolIndex()).append("\t")
//                    .append(tool.getToolName()).append("\t")
//                    .append(tool.getToolType()).append(System.lineSeparator());
//        }
//        return formattedData.toString();
//    }


    private String getMatchingInsertQty(Tool t) {
        List<ToolInsert> refreshedToolInserts = toolInsertDAO.getAllToolInserts();
        Optional<ToolInsert> first = refreshedToolInserts.stream()
                .filter(a ->t.getInsertMatching().equals(a.getInsertIndex()))
                .findFirst();

       if(first.isPresent()) {

           return String.valueOf(first.get().getInsertQty());
       }
       else {
           return "";
       }
    }




    @FXML
    private void handleKnivesButton() {
        filterByToolType("NozeTokZew");
    }

    @FXML
    private void handleDrillsButton() {
        filterByToolType("WIERTLAPLYTK");
    }

    @FXML
    private void handleTapsButton() {
        filterByToolType("Gwintowniki");
    }

    @FXML
    private void handleHeadsButton() {
        filterByToolType("GlowiceFF");
    }

    @FXML
    private void allToolsButton() {
        List<Tool> allTools = toolDAO.getAllTools();
        ObservableList<Tool> toolData = FXCollections.observableArrayList(allTools);
        toolTable.setItems(getFilteredToolData(toolData));

    }

    @FXML
    private void inSharpenButton() {
        List<Tool> allTools = toolDAO.getAllTools();
        List<Tool> toolsToSharpen = allTools.stream()
                .filter(a -> a.getToolStatus().equals("Do ostrzenia"))
                .collect(Collectors.toList());

        ObservableList<Tool> toolData = FXCollections.observableArrayList(toolsToSharpen);
        toolTable.setItems(getFilteredToolData(toolData));

    }
    @FXML
    private void allInsertButton() {
        List<ToolInsert> allToolInserts = toolInsertDAO.getAllToolInserts();
        ObservableList<ToolInsert> toolInserts = FXCollections.observableArrayList(allToolInserts);
        insertTable.setItems(toolInserts);
    }





    @FXML
    private void handleLatheOd() {
        filterByInsertType("NozeTokZew");
    }
    @FXML
    private void handleLatheId() {
        filterByInsertType("NozeTokWew");
    }

    @FXML
    private void handleFaceMillFF() {
        filterByInsertType("GlowiceFF");
    }

    @FXML
    private void handleFaceMillNM() {
        filterByInsertType("GlowiceNM");
    }
    @FXML
    private void handleInsertDrill() {
        filterByInsertType("WIERTLAPLYTK");
    }
    @FXML
    private void handleInsertFaceGroove() {
        filterByInsertType("PrzecLis");
    }
    @FXML
    private void handleOdGroove() {
        filterByInsertType("RowkiCzol");
    }

    @FXML
    private void handleInsertThreads() {
        filterByInsertType("Gwinty");
    }











    @FXML
    private void showNewEmMet(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/addviews/AddEmMet.fxml"));
            Parent root = loader.load();
            AddEmMetController addEmMetController = loader.getController();
            addEmMetController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500,430));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }

    @FXML
    private void showNewNewEmAlu(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/addviews/AddEmAlu.fxml"));
            Parent root = loader.load();
            AddEmAluController addEmAluController = loader.getController();
            addEmAluController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500,430));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }


    @FXML
    private void showNewOtherToolWindow(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/addviews/AddVariousTool.fxml"));
            Parent root = loader.load();
            AddVariousToolController addVariousToolController = loader.getController();
            addVariousToolController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500,430));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }




    @FXML
    private void showNewEmR(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/addviews/AddEmR.fxml"));
            Parent root = loader.load();
            AddEmRController addEmRController = loader.getController();
            addEmRController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500,510));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }

    @FXML
    private void showNewDrillBlade(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/addviews/AddBladeDrill.fxml"));
            Parent root = loader.load();
            AddDrillBladeController addDrillBladeController = loader.getController();
            addDrillBladeController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500,430));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }


    @FXML
    private void showNewDrVhmWindow(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/addviews/AddDrillVHM.fxml"));
            Parent root = loader.load();
            AddDrillVhmController addDrillVhmController = loader.getController();
            addDrillVhmController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500,430));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }


    @FXML
    private void showNewDrHssWindow(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/addviews/AddDrillHSS.fxml"));
            Parent root = loader.load();
            AddDrillHssController addDrillHssController = loader.getController();
            addDrillHssController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500,430));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }


    @FXML
    private void showNewTapPRWindow(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/addviews/AddTapPR.fxml"));
            Parent root = loader.load();
            AddTapPRController addTapPRController = loader.getController();
            addTapPRController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500,430));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }





    @FXML
    private void showNewTapSKWindow(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/addviews/AddTapSK.fxml"));
            Parent root = loader.load();
            AddTapSKController addTapSKController = loader.getController();
            addTapSKController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500,430));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }




    @FXML
    private void showNewTapINCHWindow(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/addviews/AddTapInch.fxml"));
            Parent root = loader.load();
            AddTapINCHController addTapINCHController = loader.getController();
            addTapINCHController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500,430));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }


    @FXML
    private void showNewThreadDieWindow(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/addviews/AddThreadDie.fxml"));
            Parent root = loader.load();
            AddThreadDieController addThreadDieControllerController = loader.getController();
            addThreadDieControllerController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500,430));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }


    @FXML
    private void showNewChamferWindow(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/addviews/AddChamfer.fxml"));
            Parent root = loader.load();
            AddChamferController addChamferController = loader.getController();
            addChamferController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500,430));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }


    @FXML
    private void showNewSpotDrillWindow(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/addviews/AddSpotDrill.fxml"));
            Parent root = loader.load();
            AddSpotDrillController addSpotDrillController = loader.getController();
            addSpotDrillController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500,430));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }




    @FXML
    private void showNewReamerWindow(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/addviews/AddReamer.fxml"));
            Parent root = loader.load();
            AddReamerController addReamerController = loader.getController();
            addReamerController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500,430));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }

    @FXML
    private void showNewIDLatheWindow(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/addviews/AddIdTool.fxml"));
            Parent root = loader.load();
            AddIDToolController addIDToolController = loader.getController();
            addIDToolController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500,430));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }


    @FXML
    private void showNewODLatheWindow(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/addviews/AddOdTool.fxml"));
            Parent root = loader.load();
            AddODToolController addODToolController = loader.getController();
            addODToolController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500,430));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }




    @FXML
    private void showNewFaceGrooveWindow(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/addviews/AddFaceGroove.fxml"));
            Parent root = loader.load();
            AddFaceGrooveToolController addFaceGrooveToolController = loader.getController();
            addFaceGrooveToolController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500,430));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }


    @FXML
    private void showNewFaceMillWindow(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/addviews/AddShellMill.fxml"));
            Parent root = loader.load();
            AddShellMillController addShellMillController = loader.getController();
            addShellMillController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500,430));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }




    @FXML
    private void showBackFromSharpenWindow(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/updateSharpenToolForm.fxml"));
            Parent root = loader.load();
            BackFromSharpenController backFromSharpenController = loader.getController();
            backFromSharpenController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500,430));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }




















































    @FXML
    private void showEditToolWindow(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/EditToolView.fxml"));
            Parent root = loader.load();
            EditToolController editToolController = loader.getController();
            editToolController.setToolsController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 300, 150));

            // Ustawienie okna na zawsze na wierzchu
            stage.setAlwaysOnTop(true);

            // Ustawienie okna z prawej strony ekranu
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            double windowWidth = 300; // szerokość okna, jaką ustawiasz
            double windowHeight = 150; // wysokość okna, jaką ustawiasz

            double x = primaryScreenBounds.getMaxX() - windowWidth - 80; // odstęp od prawej krawędzi
            double y = primaryScreenBounds.getMinY() + (primaryScreenBounds.getHeight() - windowHeight) / 2;

            stage.setX(x);
            stage.setY(y);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }



    @FXML
    private void showEditInsertWindow(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/EditInsertView.fxml"));
            Parent root = loader.load();

            EditInsertController editInsertController = loader.getController();

            // Przekaż referencję do ToolsController
            editInsertController.setToolsController(this);


            Stage stage = new Stage();
            stage.setScene(new Scene(root, 400,300));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }

    }


    @FXML
    private void showAddProducent(ActionEvent event) {

        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/AddProducent.fxml"));
            Parent root = loader.load();



            AddProducentController addProducentController = loader.getController();
            addProducentController.setToolsController(this);


            // Przekaż referencję do ToolsController


            Stage stage = new Stage();
            stage.setScene(new Scene(root, 300,200));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }

    }


    @FXML
    private void  showAddingPartNum(ActionEvent event) {

        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/AddPartView.fxml"));
            Parent root = loader.load();



            AddPartController addPartNumberController = loader.getController();
            addPartNumberController.setToolsController(this);


            // Przekaż referencję do ToolsController


            Stage stage = new Stage();
            stage.setScene(new Scene(root, 400,300));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }

    }








    @FXML
    private void showAddingInsertsToPart(ActionEvent event) {

        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/addInsertToPartView.fxml"));
            Parent root = loader.load();



            AddInsertToPartController addInsertToPartController = loader.getController();
            addInsertToPartController.setToolsController(this);


            // Przekaż referencję do ToolsController


            Stage stage = new Stage();
            stage.setScene(new Scene(root, 400,300));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }

    }



    @FXML
    private void showNewToolWindow(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/newToolForm.fxml"));
            Parent root = loader.load();

           NewToolController newToolController = loader.getController();

            // Przekaż referencję do ToolsController
            newToolController.setToolsController(this);


            Stage stage = new Stage();
            stage.setScene(new Scene(root, 400,300));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }

    }

    @FXML
    private void showNewInsertWindow(ActionEvent event) {
        try {





            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/NewInsertForm.fxml"));
            Parent root = loader.load();

            NewInsertController newInsertController = loader.getController();

            // Przekaż referencję do ToolsController
            newInsertController.setToolsController(this);


            Stage stage = new Stage();
            stage.setScene(new Scene(root, 400,300));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }

    }







    @FXML
    private void backToHomePage(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/StartView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root, 770, 500));
            stage.centerOnScreen();
            stage.show();



        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }


    @FXML
    private void actionsHistoryPage(ActionEvent event) {
        try {
            // Ładowanie widoku narzędzi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/ActionsHistoryView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root, 800, 600));
            stage.show();



        } catch (IOException e) {
            e.printStackTrace();
            // Obsługa błędów ładowania widoku
        }
    }





//     Metoda odpowiedzialna za przypisanie przycisku do narzędzia, otworzenie nowego okna z nowym plikiem FXML
    Callback<TableColumn<Tool1, String>, TableCell<Tool1, String>> setToolInfoBtn
            = //
            new Callback<TableColumn<Tool1, String>, TableCell<Tool1, String>>() {
                @Override
                public TableCell call(final TableColumn<Tool1, String> param) {
                    final TableCell<Tool1, String> cell = new TableCell<Tool1, String>() {

                        final Button btn = new Button("Tool Info");

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btn.setOnAction(event ->  {
                                    try {
                                        // Ładowanie widoku narzędzi
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tooltracker/ToolInfoView.fxml"));
                                        Parent root = loader.load();
                                            Tool1 selectedTool = getTableView().getItems().get(getIndex());
                                        ToolInfoController toolInfoController = loader.getController();
                                        toolInfoController.setToolIndex(selectedTool.getToolIndex());
                                        System.out.println(selectedTool.getToolIndex());

                                        Stage stage = new Stage();
                                        stage.setScene(new Scene(root, 400,300));
                                        stage.show();

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        // Obsługa błędów ładowania widoku
                                    }
                                }

                                );
                                setGraphic(btn);
                                setText(null);
                            }
                        }
                    };
                    return cell;
                }
            };










//    public Callback<TableColumn<Tool1, String>, TableCell<Tool1, String>> createImageButtonCellFactory(String tabName) {
//        return new Callback<TableColumn<Tool1, String>, TableCell<Tool1, String>>() {
//            @Override
//            public TableCell<Tool1, String> call(TableColumn<Tool1, String> param) {
//                return new TableCell<Tool1, String>() {
//                    final Button imageButton = new Button();
//
//                    {
//                        imageButton.setPrefSize(5, 5);
//                        imageButton.setGraphic(new ImageView(new Image("file:C:\\Users\\gercu\\Downloads\\employeemanager\\ToolTracker\\src\\main\\resources\\com\\example\\tooltracker\\icons\\rem.jpg")));
//                        imageButton.setStyle("-fx-background-color: white;");
//                        imageButton.setOnMouseEntered(event -> imageButton.getScene().setCursor(Cursor.HAND));
//                        imageButton.setOnMouseExited(event -> imageButton.getScene().setCursor(Cursor.DEFAULT));
//                        imageButton.setOnAction(event -> {
//                            if (!isEmpty()) {
//                                Tool1 selectedToolInsert = getTableRow().getItem();
//                                String toolIndex = selectedToolInsert.getToolIndex();
//
//                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                                alert.setTitle("Potwierdzenie usunięcia");
//                                alert.setHeaderText("Czy na pewno chcesz usunąć narzędzie?");
//                                alert.setContentText("Indeks narzędzia: " + toolIndex);
//
//                                Optional<ButtonType> result = alert.showAndWait();
//                                if (result.isPresent() && result.get() == ButtonType.OK) {
//                                    toolDAO.deleteTool(toolIndex, tabName);
//                                    ToolAction toolAction = new ToolAction();
//                                    toolAction.settAction("Usunięto narzędzie");
//                                    toolAction.settIndex(toolIndex);
//                                    actionDAO.addAction(toolAction);
//                                    getTableView().getItems().remove(selectedToolInsert);
//                                }
//                            }
//                        });
//                    }
//
//                    @Override
//                    protected void updateItem(String item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty) {
//                            setGraphic(null);
//                            setText(null);
//                        } else {
//                            setGraphic(imageButton);
//                            setText(null);
//                        }
//                    }
//                };
//            }
//        };
//    }




    public Callback<TableColumn<ToolInsert, String>, TableCell<ToolInsert, String>> createImageButtonForAddingInserts(){
        return new Callback<TableColumn<ToolInsert, String>, TableCell<ToolInsert, String>>() {
            @Override
            public TableCell<ToolInsert, String> call(TableColumn<ToolInsert, String> param) {
                return new TableCell<ToolInsert, String>() {
                    final Button imageButton = new Button();

                    {
                        imageButton.setGraphic(new ImageView(new Image(String.valueOf(getClass().getResource("/com/example/tooltracker/icons/addIcon2.png")))));

                        imageButton.setStyle("-fx-background-color: white;");
                        imageButton.setOnMouseEntered(event -> imageButton.getScene().setCursor(Cursor.HAND));
                        imageButton.setOnMouseExited(event -> imageButton.getScene().setCursor(Cursor.DEFAULT));
                        imageButton.setOnAction(event -> {
                            if (!isEmpty()) {
                                ToolInsert selectedToolInsert = getTableRow().getItem();
                                String toolIndex = selectedToolInsert.getInsertIndex();


                                TextInputDialog quantityDialog = new TextInputDialog();
                                quantityDialog.setTitle("Zaktualizuj stan magazynowy płytek");
                                quantityDialog.setHeaderText("Zaktualizuj płytki " + toolIndex);
                                quantityDialog.setContentText("Wpisz ilość:");

                                Optional<String> result = quantityDialog.showAndWait();
                                result.ifPresent(quantityStr -> {
                                    try {

                                        int quantityToAdd = Integer.parseInt(quantityStr);
                                        if (quantityToAdd > 0) {
                                            // Update the quantity in the database
                                            int newQuantity = selectedToolInsert.getInsertQty() + quantityToAdd;
                                            selectedToolInsert.setInsertQty(newQuantity);

                                            ToolInsertDAO toolInsertDAO = new ToolInsertDAO();
                                            toolInsertDAO.updateToolInsertQty(selectedToolInsert);

                                            InsertAction insertAction = new InsertAction(toolIndex, "Dodano " + quantityToAdd + "szt. " + toolIndex , LocalDateTime.now());
                                            insertActionDAO.addAction(insertAction);

                                            // Optionally, update the table view
                                            getTableView().refresh();
                                        } else {
                                            showAlert(Alert.AlertType.ERROR, "Nieprawidłowe dane", "Liczba musi być większa od 0");
                                        }
                                    } catch (NumberFormatException e) {
                                        showAlert(Alert.AlertType.ERROR, "Nieprawidłowa liczba", "Akceptowane są tylko cyfry");
                                    }
                                });
                            }
                        });
                    }

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            setGraphic(imageButton);
                            setText(null);
                        }
                    }
                };
            }
        };
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }









//OBRAZEK Z RODZAJEM PŁYTKI
Callback<TableColumn<ToolInsert, String>, TableCell<ToolInsert, String>> setInsertImage = new Callback<TableColumn<ToolInsert, String>, TableCell<ToolInsert, String>>() {
    @Override
    public TableCell<ToolInsert, String> call(final TableColumn<ToolInsert, String> param) {
        final TableCell<ToolInsert, String> cell = new TableCell<ToolInsert, String>() {

            final ImageView imageView = new ImageView(); // Tworzymy ImageView

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    ToolInsert selectedTool = getTableView().getItems().get(getIndex());

                    // Ustawienie obrazka w zależności od warunków
                    if ("W".equals(selectedTool.getInsertType())) {
                        Image imageW = new Image("C:\\Users\\gercu\\Downloads\\employeemanager\\ToolTracker\\src\\main\\resources\\com\\example\\tooltracker\\icons\\plytkaW.jpg");
                        imageView.setImage(imageW);
                    } else if ("L".equals(selectedTool.getInsertType())) {
                        Image imageL = new Image("C:\\Users\\gercu\\Downloads\\employeemanager\\ToolTracker\\src\\main\\resources\\com\\example\\tooltracker\\icons\\plytkaL.png");
                        imageView.setImage(imageL);
                    } else {
                        // Domyślny obrazek lub brak obrazka dla innych warunków
                        imageView.setImage(null);
                    }

                    setGraphic(imageView);
                    setText(null);
                }
            }
        };
        return cell;
    }
};


//Podswietlanie Statusów
    Callback<TableColumn<Tool1, ToolStatus>, TableCell<Tool1, ToolStatus>> setBackgroundOnStatus
            = //
            new Callback<TableColumn<Tool1, ToolStatus>, TableCell<Tool1, ToolStatus>>() {
                @Override
                public TableCell call(final TableColumn<Tool1, ToolStatus> param) {
                    final TableCell<Tool1, ToolStatus> cell = new TableCell<Tool1, ToolStatus>() {
                        @Override
                        public void updateItem(ToolStatus item, boolean empty) {
                            super.updateItem(item, empty);
                            setText(String.valueOf(item));
                            String item1 = (String.valueOf(item));


                            if (item1 != null && item1.equals("W użyciu")) {
                                setStyle(getStyle() + "-fx-background-color: white, #38e61e; " +
                                        "-fx-background-insets: 0, 3;");
                            }
                            if (item1 != null && item1.equals("W ostrzeniu")) {
                                setStyle(getStyle() + "-fx-background-color: white, blue; " +
                                        "-fx-background-insets: 0, 3;");
                            }
                            if (item1 != null && item1.equals("Zużyte")) {
                                setStyle(getStyle() + "-fx-background-color: white, red; " +
                                        "-fx-background-insets: 0, 3;");
                            }
                            if(item==null) {
                                setStyle("");
                                setText("");
                            }
                        }
                    };
                    return cell;
                }
            };



    Callback<TableColumn<ToolInsert, Integer>, TableCell<ToolInsert, Integer>> setColourInInsertUsage =
            new Callback<TableColumn<ToolInsert, Integer>, TableCell<ToolInsert, Integer>>() {
                @Override
                public TableCell<ToolInsert, Integer> call(final TableColumn<ToolInsert, Integer> param) {
                    return new TableCell<ToolInsert, Integer>() {
                        @Override
                        public void updateItem(Integer item, boolean empty) {
                            super.updateItem(item, empty);

                            // Ensure the cell is associated with a TableRow
                            if (getTableRow() != null) {
                                ToolInsert selectedToolInsert = getTableRow().getItem();

                                if (selectedToolInsert != null) {
                                    double expQty = selectedToolInsert.getExpectedQty();
                                    double liczba;

                                    if (item == null || item.equals("")) {
                                        setStyle("");
                                    } else {
                                        liczba = Double.valueOf(item);
                                        if (liczba <= expQty * 0.4) {
                                            setStyle(getStyle() + "-fx-background-color: white, #f08707; " +
                                                    "-fx-background-insets: 0, 3;");
                                        } else if (liczba > expQty * 0.4 && liczba < expQty * 0.6) {
                                            setStyle(getStyle() + "-fx-background-color: white, #f1f520; " +
                                                    "-fx-background-insets: 0, 3;");
                                        } else if (liczba > expQty * 0.6) {
                                            setStyle(getStyle() + "-fx-background-color: white, #38e61e; " +
                                                    "-fx-background-insets: 0, 3;");
                                        }
                                    }

                                    setText(String.valueOf(item));
                                } else {
                                    setStyle("");
                                    setText("");
                                }
                            } else {
                                setStyle("");
                                setText("");
                            }
                        }
                    };
                }
            };


    private void filterByToolType(String toolType) {
        List<Tool> allTools = toolDAO.getAllTools();
        ObservableList<Tool> filteredTools = FXCollections.observableArrayList(
                allTools.stream()
                        .filter(tool -> toolType.toLowerCase().equals(tool.getToolType().toLowerCase()))
                        .collect(Collectors.toList())
        );

        filteredTools.forEach(t -> t.setInsertQty(getMatchingInsertQty(t)));

        toolTable.setItems(getFilteredToolData(filteredTools));
    }
    private void filterByInsertType(String toolsMatch) {

        List<ToolInsert> allInsert = toolInsertDAO.getAllToolInserts();
        ObservableList<ToolInsert> filteredInserts = FXCollections.observableArrayList(
                allInsert.stream()
                        .filter(toolInsert ->toolsMatch.toLowerCase().equals(toolInsert.getToolsMatch().toLowerCase()))
                        .collect(Collectors.toList())
        );
        insertTable.setItems(filteredInserts);

    }







    public void refreshToolTable(String toolName) throws SQLException {

        switch (toolName.toLowerCase()) {
            case "emmet":
                ObservableList<EmMet> emMet = FXCollections.observableArrayList(emMetDAO.getAllEmMetTools());
                EmMetTable.setItems(emMet);
                break;
            case "chamfer":
                ObservableList<Chamfer> chamfers = FXCollections.observableArrayList(chamferDAO.getAllChemferTools());

                ChamferTable.setItems(chamfers);
                break;
            case "drillblades":
                ObservableList<DrillBlades> drillBlades = FXCollections.observableArrayList(drillBladesDAO.getAllDrillBlades());

                DrillBladeTable.setItems(drillBlades);
                break;
            case "drillhss":
                ObservableList<DrillHSS> drillHss = FXCollections.observableArrayList(drillHssDAO.getAllDrillHSS());

                DrillHSSTable.setItems(drillHss);
                break;
            case "drillvhm":
                ObservableList<DrillVHM> drillVhm = FXCollections.observableArrayList(drillvhmDAO.getAllDrillHSS());

                DrillVHMTable.setItems(drillVhm);
                break;
            case "emalu":
                ObservableList<EmAlu> emAlu = FXCollections.observableArrayList(emALUDAO.getAllEmAluTools());

                EmAluTable.setItems(emAlu);
                break;
            case "emr":
                ObservableList<EmR> emR = FXCollections.observableArrayList(emRDAO.getAllEmRTools());

                EmRTable.setItems(emR);
                break;
            case "facegroove":
                ObservableList<FaceGroove> faceGrooves = FXCollections.observableArrayList(grooveDAO.getAllFaceGrooveTools());

                FaceGrooveTable.setItems(faceGrooves);
                break;
            case "reamer":
                ObservableList<Reamer> reamers = FXCollections.observableArrayList(reamerDAO.getAllReamerTools());

                ReamerTable.setItems(reamers);
                break;
            case "shellmillff":
                ObservableList<ShellMill> shellFFMills = FXCollections.observableArrayList(shellMillDAO.getAllFFShellMill());

                ShelMillFFTable.setItems(shellFFMills);
                break;
            case "shellmillnm":
                ObservableList<ShellMill> shellNMMills = FXCollections.observableArrayList(shellMillDAO.getAllNMShellMill());

                ShelMillNMTable.setItems(shellNMMills);
                break;
            case "spotdrill":
                ObservableList<SpotDrill> spotDrills = FXCollections.observableArrayList(spotDrillDAO.getAllSpotDrillTools());

                SpotDRtable.setItems(spotDrills);
                break;
            case "tapinch":
                ObservableList<TapInch> tapInches = FXCollections.observableArrayList(tapInchDAO.getallTapInch());

                TapInchTable.setItems(tapInches);
                break;
            case "tappr":
                ObservableList<TapPR> tapPR = FXCollections.observableArrayList(tapPrDAO.getAllPrTap());

                TapPRTable.setItems(tapPR);
                break;
            case "tapsk":
                ObservableList<TapSK> tapSK = FXCollections.observableArrayList(tapSkDAO.getAllSkTap());

                TapSKTable.setItems(tapSK);
                break;
            case "threaddie":
                ObservableList<ThreadDie> threadDies = FXCollections.observableArrayList(threadDieDAO.getAllThreadDie());

                DieTable.setItems(threadDies);
                break;
            case "turningid":
                ObservableList<TurningID> turningIDS = FXCollections.observableArrayList(idTurningDAO.getAllIdTurnTools());

                IdLatheTable.setItems(turningIDS);
                break;
            case "turningod":
                ObservableList<TurningOD> turningODS = FXCollections.observableArrayList(odTurningDAO.getAllOdTurnTools());
                OdLatheTable.setItems(turningODS);
                break;
            default:
                throw new IllegalArgumentException("Invalid tool name: " + toolName);
        }
    }


    public String getToolNameFromIndex(String toolIndex) {
        if (toolIndex.startsWith("TS-P")) return "tappr";
        if (toolIndex.startsWith("TT-P")) return "tapsk";
        if (toolIndex.startsWith("TC-P")) return "tapinch";
        if (toolIndex.startsWith("LT-I-")) return "turningid";
        if (toolIndex.startsWith("LT-IE")) return "facegroove";
        if (toolIndex.startsWith("R")) return "reamer";
        if (toolIndex.startsWith("LT-E-")) return "turningod";
        if (toolIndex.startsWith("CM")) return "chamfer";
        if (toolIndex.startsWith("CD")) return "spotdrill";
        if (toolIndex.startsWith("DR-HSS")) return "drillhss";
        if (toolIndex.startsWith("DR-VHM")) return "drillvhm";
        if (toolIndex.startsWith("H-DR")) return "drillblades";
        if (toolIndex.startsWith("DM")) return "threaddie";
        if (toolIndex.startsWith("EM-N")) return "emalu";
        if (toolIndex.startsWith("EM-P")) return "emmet";
        if (toolIndex.startsWith("EM-R")) return "emr";
        if (toolIndex.startsWith("BM")) return "emr";
        if (toolIndex.startsWith("H-FF")) return "shellmillff";
        if (toolIndex.startsWith("H-NM")) return "shellmillnm";
        return null; // Or handle the default case appropriately
    }





    public void refreshInsertTable() {
        List<ToolInsert> allToolInserts1 = toolInsertDAO.getAllToolInserts();
        ObservableList<ToolInsert> insertData = FXCollections.observableArrayList(allToolInserts1);


        insertTable.setItems(insertData);
    }

    private FilteredList<Tool> getFilteredToolData(List<Tool> allTools) {
        ObservableList<Tool> toolData = FXCollections.observableArrayList(allTools);
        toolTable.setItems(toolData);
        FilteredList<Tool> filteredToolData = new FilteredList<>(toolData, b -> true);
        toolSearch.textProperty().addListener((observable, oldValue, newValue) ->
                filteredToolData.setPredicate(tool -> {
                    if(newValue.isEmpty() || newValue.isBlank() || newValue==null) {
                        return true;
                    }
                    String searchKewWord = newValue.toLowerCase();
                    if (tool.getToolIndex().toLowerCase().indexOf(searchKewWord) > -1) {
                        return true;
                    }
                    else if (tool.getToolName().toLowerCase().indexOf(searchKewWord) > -1) {
                        return true;
                    }
                    else {
                        return false;
                    }
                })
        );
        filteredToolData.forEach(t -> t.setInsertQty(getMatchingInsertQty(t)));
        return filteredToolData;
    }

}


