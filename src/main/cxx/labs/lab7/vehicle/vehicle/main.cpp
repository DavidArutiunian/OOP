#include "CPoliceMan.h"
#include "CPoliceCar.h"
#include <iostream>
#include <cassert>

int main()
{
    {
        const std::shared_ptr<IPoliceMan> pJohnSmith(new CPoliceMan("John Smith", "Northwest Police Station"));
        const std::shared_ptr<IPoliceMan> pJimClark(new CPoliceMan("Jim Clark", "Southeast Police Station"));
        CPoliceCar policeCar(5, FORD);
        policeCar.AddPassenger(pJohnSmith);
        policeCar.AddPassenger(pJimClark);

        assert(policeCar.GetPassengerCount() == 2);
        assert(policeCar.GetPlaceCount() == 5);
        assert(policeCar.GetCarModel() == FORD);

        assert(policeCar.GetPassenger(0).GetName() == "John Smith");
        assert(policeCar.GetPassenger(0).GetName() == "Northwest Police Station");

        std::cout
            << policeCar.GetPassenger(0).GetName()
            << "\t\t"
            << policeCar.GetPassenger(0).GetDepartmentName()
            << std::endl;

        assert(policeCar.GetPassenger(0).GetName() == "Jim Clark");
        assert(policeCar.GetPassenger(0).GetName() == "Southeast Police Station");

        std::cout
            << policeCar.GetPassenger(1).GetName()
            << "\t\t"
            << policeCar.GetPassenger(1).GetDepartmentName()
            << std::endl;
    }
}
