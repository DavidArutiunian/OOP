#pragma once
#include <list>
#include "IVehicle.h"

template <typename Base, typename Passenger>
class CVehicleImpl : public Base
{
    const size_t m_placeCount;
    const std::list<Passenger> m_passengers;

    void CheckPassengersBounds(size_t index) const;

public:
    CVehicleImpl(size_t placeCount);

    ~CVehicleImpl();

    void AddPassenger(std::shared_ptr<Passenger> pPassenger);

    Passenger const& GetPassenger(size_t index) const;

    size_t GetPassengerCount() const;

    bool IsEmpty() const;

    bool IsFull() const;

    void RemoveAllPassengers();

    void RemovePassenger(size_t index);

    size_t GetPlaceCount() const;
};
