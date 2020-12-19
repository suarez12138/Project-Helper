package multiAdapter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Adapter implements FileOperateInterfaceV2 {
    private FileOperateInterfaceV1 fileAdaptee;
    private ManageStaffInterface manageAdaptee;

    public Adapter(FileOperateInterfaceV1 fileAdaptee, ManageStaffInterface manageAdaptee) {
        this.fileAdaptee = fileAdaptee;
        this.manageAdaptee = manageAdaptee;
    }

    @Override
    public List<StaffModel> readAllStaff() {
        return fileAdaptee.readStaffFile();
    }

    @Override
    public void writeByRoom(List<StaffModel> list) {
        list = list.stream().sorted(Comparator.comparing(StaffModel::getRoom)).collect(Collectors.toList());
        fileAdaptee.writeStaffFile(list);
    }

    @Override
    public void addNewStaff(List<StaffModel> list) {
        manageAdaptee.addingStaff(list);
    }

    @Override
    public void removeStaffByName(List<StaffModel> list) {
        manageAdaptee.removeStaff(list);
    }

    @Override
    public void writeByName(List<StaffModel> list) {
        list = list.stream().sorted(Comparator.comparing(StaffModel::getName)).collect(Collectors.toList());
        fileAdaptee.writeStaffFile(list);
    }

    @Override
    public void listAllStaff(List<StaffModel> list) {
        fileAdaptee.printStaffFile(list);
    }
}
