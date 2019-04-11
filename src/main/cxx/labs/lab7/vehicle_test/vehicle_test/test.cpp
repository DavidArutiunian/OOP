#include "pch.h"

TEST(ComplexScenario, TestStory)
{
    const std::shared_ptr<IPoliceMan> pJohnSmith(new CPoliceMan("John Smith", "Northwest Police Station"));
    const std::shared_ptr<IPoliceMan> pJimClark(new CPoliceMan("Jim Clark", "Southeast Police Station"));
    CPoliceCar policeCar(5, ECarModel::FORD);
    policeCar.AddPassenger(pJohnSmith);
    policeCar.AddPassenger(pJimClark);

    EXPECT_EQ(2, policeCar.GetPassengerCount());
    EXPECT_EQ(5, policeCar.GetPlaceCount());
    EXPECT_EQ(ECarModel::FORD, policeCar.GetCarModel());

    EXPECT_EQ("John Smith", policeCar.GetPassenger(0).GetName());
    EXPECT_EQ("Northwest Police Station", policeCar.GetPassenger(0).GetDepartmentName());

    EXPECT_EQ("Jim Clark", policeCar.GetPassenger(1).GetName());
    EXPECT_EQ("Southeast Police Station", policeCar.GetPassenger(1).GetDepartmentName());

    policeCar.RemovePassenger(1);

    EXPECT_EQ(1, policeCar.GetPassengerCount());
    EXPECT_EQ("John Smith", policeCar.GetPassenger(0).GetName());

    const std::shared_ptr<IRacer> pMichaelSchumacher(new CRacer("Michael Schumacher", 322));
    const std::shared_ptr<IPerson> pRajaGandhi(new CPerson("Raja Gandhi"));
    CTaxi taxiCar(2, ECarModel::TOYOTA);
    taxiCar.AddPassenger(pRajaGandhi);
    taxiCar.AddPassenger(pMichaelSchumacher);

    EXPECT_EQ(2, taxiCar.GetPassengerCount());
    EXPECT_EQ(2, taxiCar.GetPlaceCount());
    EXPECT_EQ(ECarModel::TOYOTA, taxiCar.GetCarModel());

    taxiCar.RemovePassenger(0);
    taxiCar.AddPassenger(pJimClark);

    EXPECT_EQ("Michael Schumacher", taxiCar.GetPassenger(0).GetName());
    EXPECT_EQ("Jim Clark", taxiCar.GetPassenger(1).GetName());

    EXPECT_THROW({ taxiCar.AddPassenger(pRajaGandhi); }, std::logic_error);
}
