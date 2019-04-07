#include "CVehicleImpl.h"
#include <stdexcept>

template <typename Base, typename Passenger>
void CVehicleImpl<Base, Passenger>::CheckPassengersBounds(size_t index) const
{
    if (index > m_placeCount || index < 0)
    {
        throw std::logic_error("Index out of bound!");
    }
}

template <typename Base, typename Passenger>
CVehicleImpl<Base, Passenger>::CVehicleImpl(size_t placeCount) : m_placeCount(placeCount),
                                                                 m_passengers(std::list<Passenger>(placeCount))
{
}

template <typename Base, typename Passenger>
CVehicleImpl<Base, Passenger>::~CVehicleImpl() = default;

template <typename Base, typename Passenger>
void CVehicleImpl<Base, Passenger>::AddPassenger(std::shared_ptr<Passenger> pPassenger)
{
    CheckPassengersBounds(m_passengers.size() + 1);
    m_passengers.push_back(*pPassenger);
}

template <typename Base, typename Passenger>
Passenger const& CVehicleImpl<Base, Passenger>::GetPassenger(size_t index) const
{
    CheckPassengersBounds(index);
    return m_passengers[index];
}

template <typename Base, typename Passenger>
size_t CVehicleImpl<Base, Passenger>::GetPassengerCount() const
{
    return m_passengers.size();
}

template <typename Base, typename Passenger>
bool CVehicleImpl<Base, Passenger>::IsEmpty() const
{
    return m_passengers.size() == 0;
}

template <typename Base, typename Passenger>
bool CVehicleImpl<Base, Passenger>::IsFull() const
{
    return m_passengers.size() == m_placeCount;
}

template <typename Base, typename Passenger>
void CVehicleImpl<Base, Passenger>::RemoveAllPassengers()
{
    m_passengers.clear();
}

template <typename Base, typename Passenger>
void CVehicleImpl<Base, Passenger>::RemovePassenger(size_t index)
{
    CheckPassengersBounds(index);
    m_passengers.erase(index);
}

template <typename Base, typename Passenger>
size_t CVehicleImpl<Base, Passenger>::GetPlaceCount() const
{
    return m_placeCount;
}
